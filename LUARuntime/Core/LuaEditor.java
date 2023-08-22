public class LuaEditor extends TextScriptingExtension {
    
    private File loadedFile = null;
    private String line = "";

        
    @Override
    public void init()
    {
    
    }
    
    @Override 
    public void replaceScript(File file)
    {
        if(hasScript()){ 
            if(saveScript()){ 
                openScript(file); 
            } else { 
                 
            } 
        } else{ 
            openScript(file); 
        } 
    }
    
    @Override 
    public void openScript(File file)
    { 
        loadedFile = file; 
  
        String code = ""; 
        try{ 
            code = FileLoader.loadTextFromFile(file); 
        } catch(Exception e){
            Console.log(e);
        }
        
        setText(code); 
    }
    
    @Override
    public void onClose()
    {
        if(saveScript())
        {
            loadedFile = null;
        }
    }
    
    @Override 
    public boolean saveScript()
    {
        if(hasScript()){
            try{ 
                FileLoader.exportTextToFile(getText(), loadedFile); 
                return true; 
             } catch (Exception e){ 
                return false; 
             } 
         } 
         
         return false; 
    }
    
    @Override 
    public boolean hasScript()
    { 
        return loadedFile != null; 
    }
    
        @Override 
     public String getTipTextForLine(int line){ 
         // return message text which will be 
         // displayed on the popup dialog 
         // when user taps on the error line number 
         // on the line counter at left side 
         return ""; 
     }
    
    @Override 
    public void onTextChanged(CharSequence charSequence, int start, int before, int count)
    { 
        line = charSequence.subSequence(start,start+count).toString(); 
    }
    
    @Override
    public TextScriptingExtension.LineTip getTipForLine(int line)
    {
        return TextScriptingExtension.LineTip.None;
    }
    
    @Override
    public boolean supportFile(File file)
    {
         if(file.getAbsolutePath().endsWith(".lua")){ 
             return true; 
         }
         
         return false;
    }
}
