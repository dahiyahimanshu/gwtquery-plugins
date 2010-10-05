/*
 * Copyright 2010 The gwtquery plugins team.
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
package gwtquery.plugins.draggable.client.plugins;

import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.GQUtils;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.Event;

import gwtquery.plugins.draggable.client.DraggableHandler;
import gwtquery.plugins.draggable.client.DraggableOptions;

/**
 * This add-on handle opacity of the helper
 * 
 * @author Julien Dramaix (julien.dramaix@gmail.com)
 * 
 */
public class OpacityPlugin implements DraggablePlugin {

  private static String OLD_OPACITY_KEY = "oldOpacity";
  private static String OPACITY_CSS_KEY = "opacity";

  public String getName() {
    return "opacity";
  }

  public boolean hasToBeExecuted(DraggableOptions options) {
    Float opacity = options.getOpacity();
    return opacity != null && opacity.floatValue() >= 0;
  }

  public void onDrag(DraggableHandler handler, Element draggableElement, Event e) {
    // do nothing
  }

  public void onStart(DraggableHandler handler, Element draggableElement,
      Event e) {
    Float opacity = handler.getOptions().getOpacity();

    GQuery $helper = handler.getHelper();

    double oldOpacity = GQUtils.cur($helper.get(0), OPACITY_CSS_KEY, true);
    $helper.data(OLD_OPACITY_KEY, oldOpacity);

    $helper.css(OPACITY_CSS_KEY, "" + opacity);

  }

  public void onStop(DraggableHandler handler, Element draggableElement, Event e) {
    GQuery $helper = handler.getHelper();

    if ($helper.data(OLD_OPACITY_KEY) == null) {
      return;
    }
    double oldOpacity = $helper.data(OLD_OPACITY_KEY, Double.class);
    $helper.css(OPACITY_CSS_KEY, "" + oldOpacity);
    $helper.removeData(OLD_OPACITY_KEY);

  }

}
