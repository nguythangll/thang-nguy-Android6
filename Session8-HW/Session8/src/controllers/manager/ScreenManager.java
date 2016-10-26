package controllers.manager;

import java.util.Stack;

/**
 * Created by asus on 10/26/2016.
 */
public interface ScreenManager {
//    private Stack<GameScreen> screenStack;
//
//    public ScreenManager() {
//        screenStack  =new Stack<>();
//    }
     void change(GameScreen gameScreen, boolean addToBackStack);
//    {
//        if(addToBackStack) {
//            screenStack.push(gameScreen);
//        }
//    }
}
