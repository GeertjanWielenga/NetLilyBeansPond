package org.netbeans.nlbp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import lilytool.pdf.dockable.DockablePdfViewer;
import org.gjt.sp.jedit.jEdit;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.UndoRedo;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@MultiViewElement.Registration(
        displayName = "#LBL_Ly_VISUAL",
        iconBase = "org/netbeans/nlbp/Icon_ly_16.gif",
        mimeType = "text/x-ly",
        persistenceType = TopComponent.PERSISTENCE_NEVER,
        preferredID = "LyVisual",
        position = 2000
)
@Messages("LBL_Ly_VISUAL=Visual")
public final class LyVisualElement extends JPanel implements MultiViewElement {

    private LyDataObject obj;
    private JToolBar toolbar = new JToolBar();
    private transient MultiViewElementCallback callback;
    private DockablePdfViewer dpv;
    private JPanel panel;
    private Sequencer sequencer = null;

    public LyVisualElement(Lookup lkp) {
        obj = lkp.lookup(LyDataObject.class);
        assert obj != null;
        initComponents();
        jEdit.initSystemProperties();
        jEdit.setProperty("options.lilytool.pdf.follow-caret", "true");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        dpv = new DockablePdfViewer();
        try {
            sequencer = MidiSystem.getSequencer();
            //http://www.lilypond.org/doc/v2.17/Documentation/notation/creating-midi-files
            ImageIcon runIcon = ImageUtilities.loadImageIcon("org/netbeans/nlbp/runProject.png", false);
            JButton playButton = new JButton(runIcon);
            playButton.setToolTipText("Play File");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        sequencer.open();
                        FileObject matchingMidi = FileUtil.findBrother(obj.getPrimaryFile(), "mid");
                        InputStream is = new BufferedInputStream(new FileInputStream(FileUtil.toFile(matchingMidi)));
                        sequencer.setSequence(is);
                        sequencer.start();
                    } catch (MidiUnavailableException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (FileNotFoundException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (InvalidMidiDataException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            });
            toolbar.add(playButton);
            ImageIcon stopIcon = ImageUtilities.loadImageIcon("org/netbeans/nlbp/stop.png", false);
            JButton stopButton = new JButton(stopIcon);
            stopButton.setToolTipText("Stop Playing File");
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sequencer.stop();
                    sequencer.close();
                }
            });
            toolbar.add(stopButton);
        } catch (MidiUnavailableException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    //http://jedit-users.narkive.com/lyChJ5VD/jedit-users-how-to-force-a-view-to-become-active
    @Override
    public String getName() {
        return "LyVisualElement";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public JComponent getVisualRepresentation() {
        final FileObject primaryFile = obj.getPrimaryFile();
        FileObject matchingPDF = FileUtil.findBrother(primaryFile, "pdf");
        if (matchingPDF == null) {
            LyUtils.command(FileUtil.toFile(primaryFile));
        }
        if (matchingPDF != null) {
            dpv.setCurrentFile(FileUtil.toFile(matchingPDF));
            dpv.refresh();
            panel.add(dpv, BorderLayout.CENTER);
        }
        return panel;
    }

    @Override
    public JComponent getToolbarRepresentation() {
        return toolbar;
    }

    @Override
    public Action[] getActions() {
        return new Action[0];
    }

    @Override
    public Lookup getLookup() {
        return obj.getLookup();
    }

    @Override
    public void componentOpened() {

    }

    @Override
    public void componentClosed() {
    }

    @Override
    public void componentShowing() {
    }

    @Override
    public void componentHidden() {
    }

    @Override
    public void componentActivated() {
    }

    @Override
    public void componentDeactivated() {
    }

    @Override
    public UndoRedo getUndoRedo() {
        return UndoRedo.NONE;
    }

    @Override
    public void setMultiViewCallback(MultiViewElementCallback callback) {
        this.callback = callback;
    }

    @Override
    public CloseOperationState canCloseElement() {
        return CloseOperationState.STATE_OK;
    }

}
