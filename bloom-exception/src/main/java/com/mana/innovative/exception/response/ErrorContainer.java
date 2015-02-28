package com.mana.innovative.exception.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement (name = "error_container")
public class ErrorContainer {

    private Error currentError;
    private List<Error> errors;

    public ErrorContainer () {

        this.instantiateErrors();
    }

    public ErrorContainer (final Error currentError) {

        this.instantiateErrors();
        this.setCurrentError(currentError);
    }

    private void instantiateErrors () {
        errors = new ArrayList<>();
    }

    public Error getCurrentError () {
        return currentError;
    }

    @XmlTransient
    public void setCurrentError (final Error currentError) {
        this.currentError = currentError;
        this.addError(currentError);
    }

    public List<Error> getErrors () {
        return errors;
    }

    @XmlElementWrapper (name = "errors")
    @XmlElement
    public void setErrors (final List<Error> errors) {
        this.errors = errors;
    }

    /**
     * This method is for adding a new currentError to the errors list object to set the current currentError object use setCurrentError
     *
     * @param error {@link Error}
     */
    public void addError (final Error error) {
        this.errors.add(error);
    }
}
