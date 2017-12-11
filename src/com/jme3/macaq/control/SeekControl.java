/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.macaq.control;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author Rickard <neph1 @ github>
 */
public class SeekControl extends AbstractControl{

    private Vector3f targetLocation;
    private final Vector3f moveVector = new Vector3f();
    private final float moveSpeed = 1;
    
    @Override
    protected void controlUpdate(float tpf) {
        if(targetLocation != null && spatial.getLocalTranslation().distance(targetLocation) > 0.1f){
            moveVector.set(targetLocation.subtract(spatial.getLocalTranslation())).normalizeLocal();
            spatial.move(moveVector.multLocal(moveSpeed * tpf));
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
    public void setTargetLocation(Vector3f targetLocation){
        this.targetLocation = targetLocation;
    }
}
