package com.khudim.validators;

import com.khudim.users.File;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;





@Component
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