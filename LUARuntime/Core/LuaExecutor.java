package JAVARuntime;

import java.util.*;
import java.text.*;
import java.net.*;
import java.math.*;
import java.io.*;
import java.nio.*;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

/**
 * @Author SpeakerFish (Discord community)
 *
 * update by Matheus S.
 */
public class LuaExecutor extends Component {
  
    public List<ProjectFile> modules = new ArrayList();
    public Globals globals = null;
    private LuaValue entire_script = null;
    public ProjectFile file = new ProjectFile(".lua");
    public File ffile;
    public String luaname = "luaj";

    @Override
    public void start()
    {
        load_script();
        LuaInvoker.invoke("start", globals);
        //luaname = getLuaName();
    }

    @Override
    public void repeat()
    {
        load_script();
        LuaInvoker.invoke("update", globals);
    }
    
    @Override
    public void stoppedRepeat()
    {
        replace_list_modules();
    }
    
    @Override
    public String getComponentTittle()
    {
        return luaname;
    }
    
    public String getLuaName()
    {
        return globals.get("luaname").tojstring();
    }
    
    public void load_script()
    {
        if(entire_script != null)
        {
            return;
        }
        
        String script_text = new String();
        
        try
        {
            for(ProjectFile module: modules)
            {
                script_text += FileLoader.loadTextFromFile(module);
            }
            
            if(ffile == null)
            {
                script_text += FileLoader.loadTextFromFile(file);
            } else {
                script_text += FileLoader.loadTextFromFile(ffile);
            }
        } catch(Exception e) {
            Console.log(e);
        }
        
        globals = LUAJUtils.getGlobals();
        entire_script = globals.load(script_text);
        
        entire_script.call();
        globals.set("myObject", CoerceJavaToLua.coerce(myObject));
        globals.set("myTransform", CoerceJavaToLua.coerce(myTransform));
        globals.set("luaname", CoerceJavaToLua.coerce(luaname));
    }
    
    public void replace_list_modules()
    {
        int key = 0;
        
        for(ProjectFile module_file: modules)
        {
            if(module_file == null || module_file.getFormat() == null || module_file.getFormat() != ".lua")
            {
                modules.set(key, new ProjectFile(".lua"));
            }
            
            key++;
        }
    }
}









// eof
