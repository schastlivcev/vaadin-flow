/*
 * Copyright 2000-2014 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.client.ui.grid.events;

import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.event.dom.client.KeyCodes;
import com.vaadin.client.ui.grid.Grid;
import com.vaadin.client.ui.grid.Grid.AbstractGridKeyEvent;
import com.vaadin.client.ui.grid.events.AbstractGridKeyEventHandler.GridKeyDownHandler;

/**
 * Represents native key down event in Grid.
 * 
 * @since
 * @author Vaadin Ltd
 */
public class GridKeyDownEvent extends AbstractGridKeyEvent<GridKeyDownHandler> {

    public GridKeyDownEvent(Grid<?> grid) {
        super(grid);
    }

    @Override
    protected void doDispatch(GridKeyDownHandler handler, GridSection section) {
        if ((section == GridSection.BODY && handler instanceof BodyKeyDownHandler)
                || (section == GridSection.HEADER && handler instanceof HeaderKeyDownHandler)
                || (section == GridSection.FOOTER && handler instanceof FooterKeyDownHandler)) {
            handler.onKeyDown(this);
        }
    }

    @Override
    protected String getBrowserEventType() {
        return BrowserEvents.KEYDOWN;
    }

    /**
     * Does the key code represent an arrow key?
     * 
     * @param keyCode
     *            the key code
     * @return if it is an arrow key code
     */
    public static boolean isArrow(int keyCode) {
        switch (keyCode) {
        case KeyCodes.KEY_DOWN:
        case KeyCodes.KEY_RIGHT:
        case KeyCodes.KEY_UP:
        case KeyCodes.KEY_LEFT:
            return true;
        default:
            return false;
        }
    }

    /**
     * Gets the native key code. These key codes are enumerated in the
     * {@link KeyCodes} class.
     * 
     * @return the key code
     */
    public int getNativeKeyCode() {
        return getNativeEvent().getKeyCode();
    }

    /**
     * Is this a key down arrow?
     * 
     * @return whether this is a down arrow key event
     */
    public boolean isDownArrow() {
        return getNativeKeyCode() == KeyCodes.KEY_DOWN;
    }

    /**
     * Is this a left arrow?
     * 
     * @return whether this is a left arrow key event
     */
    public boolean isLeftArrow() {
        return getNativeKeyCode() == KeyCodes.KEY_LEFT;
    }

    /**
     * Is this a right arrow?
     * 
     * @return whether this is a right arrow key event
     */
    public boolean isRightArrow() {
        return getNativeKeyCode() == KeyCodes.KEY_RIGHT;
    }

    /**
     * Is this a up arrow?
     * 
     * @return whether this is a right arrow key event
     */
    public boolean isUpArrow() {
        return getNativeKeyCode() == KeyCodes.KEY_UP;
    }

    @Override
    public String toDebugString() {
        return super.toDebugString() + "[" + getNativeKeyCode() + "]";
    }
}
