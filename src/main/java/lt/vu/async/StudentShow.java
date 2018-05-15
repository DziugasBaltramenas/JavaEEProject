package lt.vu.async;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Named
@SessionScoped
@Slf4j
public class StudentShow implements Serializable {
    @Inject
    private StudentFinder finder;

    private Future<String> resultInFuture = null;
    @Setter
    @Getter
    private String name;

    @Getter
    private String status;
    @Getter
    private boolean showStatus;
    @Getter
    private boolean enableRegister = true;

    public void find() throws ExecutionException, InterruptedException {
        showStatus = true;
        if (name == null)
            status = "Name not entered...";
        enableRegister = false;
        resultInFuture = finder.findStudent(name);
        status = "Please wait... registering visitor.";
    }

    public void updateStatus() throws ExecutionException, InterruptedException {
        if (resultInFuture == null)
            status = "Finder not in progress";

        if (resultInFuture.isDone()) {
            String result = resultInFuture.get();
            resultInFuture = null;
            enableRegister = true;
            status = "Student found!!! " + result;
        } else {
            status = "Result is not yet ready... please wait a moment...";
        }
    }




}
