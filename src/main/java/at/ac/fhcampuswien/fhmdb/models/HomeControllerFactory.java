package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.contoller.HomeController;

public class HomeControllerFactory implements ControllerFactory{

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
