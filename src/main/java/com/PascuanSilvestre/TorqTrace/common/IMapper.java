package com.PascuanSilvestre.TorqTrace.common;

public interface IMapper<E, Q, S> {
    E toEntity(Q request);
    S toResponse(E entity);
}
