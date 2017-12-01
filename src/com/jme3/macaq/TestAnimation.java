/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.macaq;

import com.jme3.app.SimpleApplication;
import com.jme3.macaq.logic.app.MacaqScriptAppState;
import com.jme3.scene.Spatial;

/**
 *
 * @author Rickard <neph1 @ github>
 */
public class TestAnimation extends SimpleApplication{
    
    public static void main(String[] args) {
        TestAnimation app = new TestAnimation();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        Spatial scene = assetManager.loadModel("Scenes/TestAnimation.j3o");
        rootNode.attachChild(scene);
        
        
        MacaqScriptAppState scriptAppState = new MacaqScriptAppState("Scripts/TestAnimation.mqs");
        stateManager.attach(scriptAppState);
    }
    
    
}
