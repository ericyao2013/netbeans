/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import org.netbeans.spi.viewmodel.Models;
import org.netbeans.spi.viewmodel.NodeModel;
import org.netbeans.spi.viewmodel.TreeModel;
import org.netbeans.spi.viewmodel.TreeModelListener;


public class TreeModelExample2 implements TreeModel, NodeModel {    
    
    public Object[] getChildren (Object parent, int from, int to) {
        if (parent == ROOT)
            return File.listRoots ();
        return ((File) parent).listFiles ();
    }
    
    public Object getRoot () {
        return ROOT;
    }
    
    public boolean isLeaf (Object node) {
        if (node == ROOT)
            return false;
        return ((File) node).isFile ();
    }
    
    public void addTreeModelListener (TreeModelListener l) {}
    public void removeTreeModelListener (TreeModelListener l) {}
    
    public String getDisplayName (Object node) {
        if (node == ROOT) return "Name";
        String name = ((File) node).getName ();
        if (name.length () < 1) return ((File) node).getAbsolutePath ();
        return name;
    }
    
    public String getIconBase (Object node) {
        if (node == ROOT) return "folder";
        if (((File) node).isDirectory ()) return "folder";
        return "file";
    }
    
    public String getShortDescription (Object node) {
        if (node == ROOT) return "Name";
        return ((File) node).getAbsolutePath ();
    }
    
    public static void main (String[] args) {
        TreeModelExample2 tme = new TreeModelExample2 ();
        JComponent ttv = Models.createView (
            tme,              // TreeModel
            tme,              // NodeModel
            null,             // TableModel
            null,             // NodeActionsProvider
            new ArrayList ()  // list of ColumnModels
        );
        JFrame f = new JFrame ("Tree Model Example 2");
        f.getContentPane ().add (ttv);
        f.pack ();
        f.show ();
    }    
}
