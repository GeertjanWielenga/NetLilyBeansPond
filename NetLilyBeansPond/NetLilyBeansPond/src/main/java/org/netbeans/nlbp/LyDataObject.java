package org.netbeans.nlbp;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.StatusDisplayer;
import org.openide.filesystems.FileAttributeEvent;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileRenameEvent;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.WeakListener;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_Ly_LOADER=Files of Ly"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_Ly_LOADER",
        mimeType = "text/x-ly",
        extension = {"ly"}
)
@DataObject.Registration(
        mimeType = "text/x-ly",
        iconBase = "org/netbeans/nlbp/Icon_ly_16.gif",
        displayName = "#LBL_Ly_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/x-ly/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    ),
    @ActionReference(
            path = "Loaders/text/x-ly/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    ),
    @ActionReference(
            path = "Loaders/text/x-ly/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    ),
    @ActionReference(
            path = "Loaders/text/x-ly/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    ),
    @ActionReference(
            path = "Loaders/text/x-ly/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    ),
    @ActionReference(
            path = "Loaders/text/x-ly/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000
    ),
    @ActionReference(
            path = "Loaders/text/x-ly/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200
    ),
    @ActionReference(
            path = "Loaders/text/x-ly/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300
    ),
    @ActionReference(
            path = "Loaders/text/x-ly/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400
    )
})
public class LyDataObject extends MultiDataObject implements FileChangeListener {

    public LyDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/x-ly", true);
        pf.addFileChangeListener(WeakListener.fileChange(this, pf));
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @MultiViewElement.Registration(
            displayName = "#LBL_Ly_EDITOR",
            iconBase = "org/netbeans/nlbp/Icon_ly_16.gif",
            mimeType = "text/x-ly",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "Ly",
            position = 1000
    )
    @Messages("LBL_Ly_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        mve = new MultiViewEditorElement(lkp);
        return mve;
    }
    
    static MultiViewEditorElement mve = null;

    @Override
    public void fileChanged(FileEvent fe) {
        mve.getVisualRepresentation().updateUI();
        StatusDisplayer.getDefault().setStatusText("Updated the GUI!", 10);
    }

    @Override
    public void fileDataCreated(FileEvent fe) {
    }

    @Override
    public void fileDeleted(FileEvent fe) {
    }

    @Override
    public void fileFolderCreated(FileEvent fe) {
    }

    @Override
    public void fileRenamed(FileRenameEvent fre) {
    }

    @Override
    public void fileAttributeChanged(FileAttributeEvent fae) {
    }

}
