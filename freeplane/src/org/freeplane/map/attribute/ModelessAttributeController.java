/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Dimitry Polivaev
 *
 *  This file author is Dimitry Polivaev
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.map.attribute;

import javax.swing.Action;

import org.freeplane.core.controller.Controller;
import org.freeplane.core.extension.IExtension;
import org.freeplane.core.filter.FilterController;
import org.freeplane.core.map.MapModel;

/**
 * @author Dimitry Polivaev
 */
public class ModelessAttributeController implements IExtension {
	public static ModelessAttributeController getController() {
		return (ModelessAttributeController) Controller.getController().getExtension(
		    ModelessAttributeController.class);
	}

	public static void install() {
		Controller.getController().addExtension(ModelessAttributeController.class,
		    new ModelessAttributeController());
		FilterController.getController().getConditionFactory().addConditionController(2,
		    new AttributeConditionController());
	}

	final private Action hideAllAttributes;
	final private Action showAllAttributes;
	final private Action showAttributeManagerAction;
	final private Action showSelectedAttributes;

	public ModelessAttributeController() {
		super();
		showAttributeManagerAction = new ShowAttributeDialogAction();
		showAllAttributes = new ShowAllAttributesAction();
		showSelectedAttributes = new ShowSelectedAttributesAction();
		hideAllAttributes = new HideAllAttributesAction();
		Controller.getController().addAction("showAttributeManagerAction",
		    showAttributeManagerAction);
		Controller.getController().addAction("showAllAttributes", showAllAttributes);
		Controller.getController().addAction("showSelectedAttributes", showSelectedAttributes);
		Controller.getController().addAction("hideAllAttributes", hideAllAttributes);
	}

	public void setAttributeViewType(final MapModel map, final String value) {
		if (value.equals(AttributeTableLayoutModel.SHOW_SELECTED)) {
			((ShowSelectedAttributesAction) showSelectedAttributes).setAttributeViewType(map);
		}
		else if (value.equals(AttributeTableLayoutModel.HIDE_ALL)) {
			((HideAllAttributesAction) hideAllAttributes).setAttributeViewType(map);
		}
		else if (value.equals(AttributeTableLayoutModel.SHOW_ALL)) {
			((ShowAllAttributesAction) showAllAttributes).setAttributeViewType(map);
		}
	}
}
