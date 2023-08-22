public class LuaInstance
{
    
    public static Object newInstance(SpatialObject object, String componentName)
    {
        LuaExecutor luaExecutor = (LuaExecutor) object.findComponent(componentName);
        
        if(luaExecutor.globals == null)
            luaExecutor.load_script();
            
        return LuaInvoker.invoke("new", luaExecutor.globals);
    }
}