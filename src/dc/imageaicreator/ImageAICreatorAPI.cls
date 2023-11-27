Class dc.imageaicreator.ImageAICreatorAPI Extends %CSP.REST
{

Parameter CHARSET = "utf-8";

Parameter CONTENTTYPE = "text/plain";

Parameter HandleCorsRequest = 1;

XData UrlMap [ XMLNamespace = "http://www.intersystems.com/urlmap" ]
{
<Routes>
<!-- Swagger specs -->
<Route Url="/_spec" Method="GET" Call="SwaggerSpec" />
<!-- Generate an image from a text sentence  -->
<Route Url="/generate" Method="POST" Call="GenerateAIImage" Cors="true"/>
</Routes>
}

ClassMethod GenerateAIImage() As %Status
{
    Set sc = $$$OK

    Set Sentence=$ZCONVERT(%request.Content.Read(),"I","UTF8")

    Set result = ##class(dc.imageaicreator.ImageAICreator).GenerateAIImage(
        Sentence,
        "/tmp/image.png"
    )
        
    Set %response.ContentType = "application/octet-stream"
    Do %response.SetHeader("Content-Disposition","attachment;filename=""image.png""")
    Set %response.NoCharSetConvert=1
    Set %response.Headers("Access-Control-Allow-Origin")="*"
    
    Set stream=##class(%Stream.FileBinary).%New()
    Set tSC=stream.LinkToFile("/tmp/image.png")
    Do stream.OutputToDevice() 
    
    Set tSC=$$$OK
    
    Return tSC
}

ClassMethod SwaggerSpec() As %Status
{
  Set tSC = ##class(%REST.API).GetWebRESTApplication($NAMESPACE, %request.Application, .swagger)
  Do swagger.info.%Remove("x-ISC_Namespace")
  Set swagger.basePath = "/image-ai-creator"
  Set swagger.info.title = "Image AI Creator"
  Set swagger.info.version = "0.1"
  Set tSC = ##class(%RoutineMgr).GetWebServerPort(.Port,.Server,.URLPrefix,.URL)
  Return ..%ProcessResult($$$OK, swagger)
}

ClassMethod %ProcessResult(pStatus As %Status = {$$$OK}, pResult As %DynamicObject = "") As %Status [ Internal ]
{
  #dim %response As %CSP.Response
  SET tSC = $$$OK
  IF $$$ISERR(pStatus) {
    SET %response.Status = 500
    SET tSC = ..StatusToJSON(pStatus, .tJSON)
    IF $isobject(tJSON) {
      SET pResult = tJSON
    } ELSE {
      SET pResult = { "errors": [ { "error": "Unknown error parsing status code" } ] }
    }
  } 
  ELSEIF pStatus=1 {
    IF '$isobject(pResult){
      SET pResult = {
      }
    }
  }
  ELSE {
    SET %response.Status = pStatus
    SET error = $PIECE(pStatus, " ", 2, *)
    SET pResult = {
      "error": (error)
    }
  }
  
  IF pResult.%Extends("%Library.DynamicAbstractObject") {
    WRITE pResult.%ToJSON()
  }
  ELSEIF pResult.%Extends("%JSON.Adaptor") {
    DO pResult.%JSONExport()
  }
  ELSEIF pResult.%Extends("%Stream.Object") {
    DO pResult.OutputToDevice()
  }
  
  QUIT tSC
}

ClassMethod ReportHttpStatusCode(pHttpStatus, pSC As %Status = {$$$OK}) As %Status
{
  Set %response.Status=pHttpStatus
  
  If $$$ISERR(pSC) Do ..outputStatus(pSC)
      
  Return $$$OK
}

}
