package org.netbeans.nlbp;

import javax.swing.JOptionPane;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.spi.editor.document.OnSaveTask;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

@MimeRegistration(mimeType = "text/x-ly", service = OnSaveTask.Factory.class, position = 1500)
public class LyOnSaveTaskFactory implements OnSaveTask.Factory{

    @Override
    public OnSaveTask createTask(final OnSaveTask.Context cntxt) {
        return new OnSaveTask() {
            @Override
            public void performTask() {
                FileObject fo = NbEditorUtilities.getFileObject(cntxt.getDocument());
                LyUtils.command(FileUtil.toFile(fo));
            }
            @Override
            public void runLocked(Runnable r) {
                performTask();
            }
            @Override
            public boolean cancel() {
                return true;
            }
        };
    }
    
}
