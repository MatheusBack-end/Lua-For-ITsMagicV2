package JAVARuntime; 
  
public class LuaIcon extends FilesPanelCustomIcon
{  
  
    @Override 
    public File getIconForFile(File file)
    { 
        return new File(Directories.getProjectFolder() + "Files/assets/lua.png"); 
    } 
  
    @Override 
    public boolean supportFile(File file)
    { 
        if (file.getAbsolutePath().endsWith(".lua"))
        { 
            return true; 
        } 
  
        return false; 
    } 
}
