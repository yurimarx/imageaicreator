Class dc.imageaicreator.ImageAICreator Extends %RegisteredObject
{

ClassMethod GenerateAIImage(Sentence As %String, Destination As %String) As %Status
{
    set javaGate = $system.external.getJavaGateway()  
    do javaGate.addToPath($SYSTEM.Util.BinaryDirectory()_"imageaicreator.jar")
    set imageAICreator = javaGate.new("imageaicreator.ImageAICreator") 
    
    set result = imageAICreator.createAIImage(
        Sentence, 
        Destination)
    
    return result
}

}
