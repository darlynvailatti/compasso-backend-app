package com.compasso.backend.app.pattern.common;

import com.compasso.backend.app.exception.BusinessLogicException;

public interface IConverter<Entrada,Saida> {
    Saida convert(Entrada entrada) throws BusinessLogicException;
}
