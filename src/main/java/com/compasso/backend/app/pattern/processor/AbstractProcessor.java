package com.compasso.backend.app.pattern.processor;

import com.compasso.backend.app.exception.BusinessLogicException;

import java.text.MessageFormat;

public abstract class AbstractProcessor<Input extends AbstractProcessorDTO, Output extends AbstractProcessorDTO>
        implements IProcessor<Input, Output> {

    protected Input input;

    protected Output output;

    @Override
    public Output execute(Input in) throws BusinessLogicException {
        try{
            saveInput(in);
            executionImplementation();
            executionReturn();
            return output;
        }catch(Exception e){
            throwNewBusinessLogicException(e);
            return null;
        }
    }

    private void saveInput(Input input) {
        this.input = input;
    }

    protected void throwNewBusinessLogicException(Exception e) throws BusinessLogicException{
        String formatedMessage = MessageFormat.format("Error while execute {0}. From: {1}", getName(), e.getMessage());
        throw new BusinessLogicException(formatedMessage);
    }

    protected abstract void executionImplementation() throws BusinessLogicException;

    protected abstract void executionReturn();

    protected abstract String getName();
}
