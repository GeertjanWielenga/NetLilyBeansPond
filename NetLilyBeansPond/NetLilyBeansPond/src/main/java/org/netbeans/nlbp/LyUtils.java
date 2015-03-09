package org.netbeans.nlbp;

import java.io.File;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.netbeans.api.extexecution.ExternalProcessBuilder;

public class LyUtils {

    //"C://Program Files (x86)//LilyPond//usr//bin//lilypond-windows.exe" -dgui
    public static void command(File lyFile) {
        String executable = new File("C://Program Files (x86)//LilyPond//usr//bin//lilypond.exe").getPath();
        ExecutionDescriptor executionDescriptor = new ExecutionDescriptor().
                frontWindow(true).
                inputVisible(true).
                controllable(true).
                showProgress(true);
        ExternalProcessBuilder externalProcessBuilder = new ExternalProcessBuilder(executable).
                workingDirectory(new File(lyFile.getParent())).
                addArgument(lyFile.getPath());
        ExecutionService service = ExecutionService.newService(
                externalProcessBuilder,
                executionDescriptor,
                "LilyPond -- " + lyFile.getName()
        );
        service.run();
    }

}
