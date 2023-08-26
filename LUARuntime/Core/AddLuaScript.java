package JAVARuntime;

public class AddLuaScript extends FilesPanelFileMenu
{
    
    public LuaExecutor luaexecutor;
    public File f;
        
    public AddLuaScript()
    {
        super("Attach to selected");
    }
    
    @Override
    public void onClick(final File file)
    {
        f = file;
        Thread.runOnEngine(new Runnable()
        {
            public void run()
            {
                if(Editor.getSelectedObject() != null)
                {
                    luaexecutor = new LuaExecutor();
                    luaexecutor.ffile = f;
                    luaexecutor.luaname = f.getName();
                    Editor.getSelectedObject().addComponent(luaexecutor);
                }
            }
        });
    }
}
