/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.macaq;

import com.jme3.app.SimpleApplication;
import com.jme3.macaq.logic.app.MacaqScriptAppState;
import com.jme3.macaq.state.SpawnAppState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Rickard <neph1 @ github>
 */
public class TestNotifyAppState extends SimpleApplication{
    
    public static void main(String[] args) {
        TestNotifyAppState app = new TestNotifyAppState();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        Node scene = (Node) assetManager.loadModel("Scenes/TestMultiMove.j3o");
        rootNode.attachChild(scene);
        
        SpawnAppState spawnAppState = new SpawnAppState();
        stateManager.attach(spawnAppState);
        
        MacaqScriptAppState macaqAppState = new MacaqScriptAppState("Scripts/TestNotifyAppState.mqs");
        stateManager.attach(macaqAppState);
        
        cam.setLocation(new Vector3f(10, 5, 10));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        
    }
}
