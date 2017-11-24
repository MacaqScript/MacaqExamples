/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.macaq.control;

import com.jme3.macaq.base.LogicInConnection;
import com.jme3.macaq.base.LogicOutConnection;
import com.jme3.macaq.control.util.ControlUtil;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.HashMap;
import java.util.Map;

/**
 * Example implementation of a Notifyable control
 * Expects the args map to contain a "message" entry, a String with the name of the target spatial to move towards.
 * @author Rickard <neph1 @ github>
 */
public class NotifyableControl extends MacaqControl implements Notifyable{

    private static final float MIN_DISTANCE = 0.1f;
    private float speed = 0.5f;
    
    private LogicInConnection message; // this is the connection through which the target is received
    private LogicOutConnection onPerformed; // this is used to send back a signal when movement is done
    
    private Spatial currentTarget;
    private int originId; // this is stored to send back in onPerformed. This way multiple Notify's can distinguish between performed actions
    
    @Override
    protected void controlUpdate(float tpf) {
        super.controlUpdate(tpf);
        if(currentTarget != null){
            Vector3f targetLocation = currentTarget.getLocalTranslation();
            if(spatial.getLocalTranslation().distance(targetLocation) > MIN_DISTANCE){
                Vector3f moveVector = targetLocation.subtract(spatial.getLocalTranslation()).normalizeLocal();
                spatial.move(moveVector.mult(speed * tpf));
                spatial.lookAt(targetLocation, Vector3f.UNIT_Y);
            } else {
                currentTarget = null;
                Map args = new HashMap<>();
                args.put("id", originId);
                onPerformed().execute(args);
            }
        }
    }
    
    @Override
    public LogicInConnection message() {
        if(message == null){
            message = new LogicInConnection((Map<String,Object> args) -> {
                Node root = ControlUtil.findRootNode(spatial);
                currentTarget = root.getChild((String)args.get("message"));
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
