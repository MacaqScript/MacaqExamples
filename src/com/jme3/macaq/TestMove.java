/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.macaq;

import com.jme3.app.SimpleApplication;
import com.jme3.macaq.logic.app.MacaqScriptAppState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rickard <neph1 @ github>
 */
public class TestMove extends SimpleApplication{
    
    public static void main(String[] args) {
        TestMove app = new TestMove();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        Node scene = (Node) assetManager.loadModel("Scenes/TestMultiMove.j3o");
        rootNode.attachChild(scene);
        
        MacaqScriptAppState macaqAppState = new MacaqScriptAppState("Scripts/TestMultiMove.mqs");
        stateManager.attach(macaqAppState);
        
        cam.setLocation(new Vector3f(10, 5, 10));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        
    }
    
}
