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
            validateInput();
            executionImplementation();
            return executionReturn();
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

    protected abstract void executionImplementation() throws Exception;

    protected abstract Output executionReturn() throws Exception;

    protected abstract void validateInput() throws Exception;

    protected abstract String getName();
}
