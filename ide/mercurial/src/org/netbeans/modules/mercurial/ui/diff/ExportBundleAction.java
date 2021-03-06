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
package org.netbeans.modules.mercurial.ui.diff;

import java.util.Set;
import org.netbeans.modules.versioning.spi.VCSContext;
import java.io.File;
import org.netbeans.modules.mercurial.Mercurial;
import org.netbeans.modules.mercurial.util.HgUtils;
import org.netbeans.modules.mercurial.ui.actions.ContextAction;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;

/**
 * ExportBundleaction for mercurial:
 * hg bundle
 *
 * @author Ondra Vrabec
 */
@NbBundle.Messages({
    "CTL_MenuItem_ExportBundle=Export &Changeset Bundle...",
    "CTL_PopupMenuItem_ExportBundle=Export Changeset Bundle..."
})
public class ExportBundleAction extends ContextAction {

    @Override
    protected boolean enable(Node[] nodes) {
        VCSContext context = HgUtils.getCurrentContext(nodes);
        Set<File> roots = context.getFiles();
        if(roots == null) return false;
        return HgUtils.isFromHgRepository(context);
    }

    @Override
    protected String getBaseName(Node[] nodes) {
        return "CTL_MenuItem_ExportBundle"; // NOI18N
    }

    @Override
    protected void performContextAction(Node[] nodes) {
        VCSContext context = HgUtils.getCurrentContext(nodes);
        final File files[] = HgUtils.getActionRoots(context);
        if (files == null || files.length == 0) return;
        final File repository = Mercurial.getInstance().getRepositoryRoot(files[0]);

        ExportBundle exportBundle = new ExportBundle(repository);
        exportBundle.export();
    }
}
