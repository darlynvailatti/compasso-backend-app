package com.compasso.backend.app.pattern.processor;

import com.compasso.backend.app.exception.BusinessLogicException;

public interface IProcessor<In extends AbstractProcessorDTO,Out extends AbstractProcessorDTO> {

    Out execute(In in) throws BusinessLogicException;
}
