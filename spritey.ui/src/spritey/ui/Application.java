/**
 * This source file is part of Spritey - the sprite sheet creator.
 * 
 * Copyright 2011 Maksym Bykovskyy.
 * 
 * Spritey is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * Spritey is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Spritey. If not, see <http://www.gnu.org/licenses/>.
 */
package spritey.ui;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import spritey.ui.dialogs.ModelessWizardDialog;
import spritey.ui.wizards.SpriteSheetWizard;

public class Application {

    public static final String EXPAND_ALL_IMG_ID = "expand_all";
    public static final String COLLAPSE_ALL_IMG_ID = "collapse_all";
    public static final String DROP_DOWN_IMG_ID = "drop_down";
    public static final String ADD_IMG_ID = "add";
    public static final String CREATE_GROUP_IMG_ID = "crate_group";
    public static final String DELETE_IMG_ID = "delete";
    public static final String FOLDER_IMG_ID = "folder";
    public static final String SHEET_IMG_ID = "sheet";
    public static final String GROUP_IMG_ID = "group";
    public static final String SPRITE_IMG_ID = "sprite";
    public static final String EDIT_IMG_ID = "edit";
    public static final String LOCK_IMG_ID = "lock";

    public static final String EXPAND_ALL_IMG_PATH = "icons/expand_all.gif";
    public static final String COLLAPSE_ALL_IMG_PATH = "icons/collapse_all.gif";
    public static final String DROP_DOWN_IMG_PATH = "icons/dropdown.gif";
    public static final String ADD_IMG_PATH = "icons/add.png";
    public static final String CREATE_GROUP_IMG_PATH = "icons/create_group.png";
    public static final String DELETE_IMG_PATH = "icons/delete.png";
    public static final String FOLDER_IMG_PATH = "icons/folder.png";
    public static final String SHEET_IMG_PATH = "icons/sheet.png";
    public static final String GROUP_IMG_PATH = "icons/group.png";
    public static final String SPRITE_IMG_PATH = "icons/sprite.png";
    public static final String EDIT_IMG_PATH = "icons/edit.png";
    public static final String LOCK_IMG_PATH = "icons/lock.png";

    private static ImageRegistry imageRegistry;

    /**
     * Returns an instance of image registry.
     * 
     * @return an instance of image registry.
     */
    public static ImageRegistry getImageRegistry() {
        if (null == imageRegistry) {
            imageRegistry = new ImageRegistry();
            initializeImageRegistry(imageRegistry);
        }

        return imageRegistry;
    }

    /**
     * Initializes specified image registry with images frequently used by the
     * application.
     * 
     * @param reg
     *        the image registry to initialize.
     */
    private static void initializeImageRegistry(ImageRegistry reg) {
        reg.put(COLLAPSE_ALL_IMG_ID, getImageDescriptor(COLLAPSE_ALL_IMG_PATH));
        reg.put(EXPAND_ALL_IMG_ID, getImageDescriptor(EXPAND_ALL_IMG_PATH));
        reg.put(DROP_DOWN_IMG_ID, getImageDescriptor(DROP_DOWN_IMG_PATH));
        reg.put(ADD_IMG_ID, getImageDescriptor(ADD_IMG_PATH));
        reg.put(CREATE_GROUP_IMG_ID, getImageDescriptor(CREATE_GROUP_IMG_PATH));
        reg.put(DELETE_IMG_ID, getImageDescriptor(DELETE_IMG_PATH));
        reg.put(FOLDER_IMG_ID, getImageDescriptor(FOLDER_IMG_PATH));
        reg.put(SHEET_IMG_ID, getImageDescriptor(SHEET_IMG_PATH));
        reg.put(GROUP_IMG_ID, getImageDescriptor(GROUP_IMG_PATH));
        reg.put(SPRITE_IMG_ID, getImageDescriptor(SPRITE_IMG_PATH));
        reg.put(EDIT_IMG_ID, getImageDescriptor(EDIT_IMG_PATH));
        reg.put(LOCK_IMG_ID, getImageDescriptor(LOCK_IMG_PATH));
    }

    /**
     * Returns image descriptor for image at the specified path.
     * 
     * @param path
     *        the path to an image.
     * @return a descriptor for specified image. When image not found returns
     *         <code>null</code>.
     */
    private static ImageDescriptor getImageDescriptor(String path) {
        return ImageDescriptor.createFromURL(Application.class
                .getResource(path));
    }

    /**
     * Redirects "standard" output and error streams to the specified file.
     * 
     * @param filename
     *        the name of the file to redirect streams to.
     * @throws FileNotFoundException
     *         If the given file object does not denote an existing, writable
     *         regular file and a new regular file of that name cannot be
     *         created, or if some other error occurs while opening or creating
     *         the file
     */
    private static void redirectSystemStreams(String filename)
            throws FileNotFoundException {
        PrintStream logger = new PrintStream(filename);
        System.setOut(logger);
        System.setErr(logger);
    }

    public static void main(String[] args) {
        try {
            redirectSystemStreams("spritey.log");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        WizardDialog w = new ModelessWizardDialog(new Shell(),
                new SpriteSheetWizard());
        w.setPageSize(750, 495);
        w.open();
    }
}
