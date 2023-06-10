package at.ac.fhcampuswien.fhmdb.contoller;

import javafx.util.Callback;

public class HomeControllerFactory implements Callback<Class<?>,Object> {

    // each ControllerFactory singleton
    // stores single one instance of controller, which it returns to javaFX
    private static HomeControllerFactory homeControllerFactoryInstance;
    private HomeController homeControllerInstance = new HomeController();
    private HomeControllerFactory(){}
    public static HomeControllerFactory getHomeControllerFactoryInstance() {
        if(homeControllerFactoryInstance ==null){
            homeControllerFactoryInstance = new HomeControllerFactory();
        }
        return homeControllerFactoryInstance;
    }
    @Override
    public Object call(Class<?> aClass) {
        try {
            return homeControllerInstance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
