/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.macaq.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.macaq.base.LogicInConnection;
import com.jme3.macaq.base.LogicOutConnection;
import com.jme3.macaq.control.Notifyable;
import com.jme3.macaq.control.SeekControl;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rickard <neph1 @ github>
 */
public class SpawnAppState extends AbstractAppState implements Notifyable {

    private LogicInConnection message; // this is the connection through which the target is received
    private LogicOutConnection onPerformed; // this is used to send back a signal when movement is done
    
    private Spatial enemy;
    private Node rootNode;
    private int originId; // this is stored to send back in onPerformed. This way multiple Notify's can distinguish between performed actions
    
    public SpawnAppState(){
        
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        enemy = app.getAssetManager().loadModel("Models/SeekingEnemy.j3o");
        rootNode = ((SimpleApplication)app).getRootNode();
    }
    
    private void spawn(){
        float random = FastMath.nextRandomFloat() * FastMath.PI;
        Vector3f spawnLocation = new Vector3f(20 * FastMath.sin(random) - 10, 0, -10 * FastMath.cos(random));
        
        Spatial newEnemy = enemy.clone();
        newEnemy.setLocalTranslation(spawnLocation);
        newEnemy.getControl(SeekControl.class).setTargetLocation(new Vector3f());
        rootNode.attachChild(newEnemy);
        
        Map args = new HashMap<>();
        args.put("id", originId);
        onPerformed().execute(args);
    }
    
    @Override
    public LogicInConnection message() {
        if(message == null){
            message = new LogicInConnection((Map<String,Object> args) -> {
                if(args.get("message").equals("spawn")){
                    spawn();
                }
                originId = (int) args.get("id");
                
            }, "message");
        }
        return message;
    }

    @Override
    public LogicOutConnection onPerformed() {
        if(onPerformed == null){
            onPerformed = new LogicOutConnection("onPerformed");
        }
        return onPerformed;
    }

}
