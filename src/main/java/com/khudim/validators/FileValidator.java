package com.khudim.validators;

import com.khudim.services.File;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;






public class FileValidator implements Validator {
    public boolean supports(Class<?> paramClass) {
        return File.class.equals(paramClass);
    }

    public void validate(Object obj, Errors errors) {
        File myFile = (File) obj;
        if (myFile.getFile().getSize() == 0) {
            errors.rejectValue("myFile", "valid.myFile");
        }
    }
}