package com.mediaymedia.commons.persistencia.actions;

/**
 *
 * User: juanitu
 * Date: 22-mar-2007
 * Time: 16:53:02
 * To change this template use File | Settings | File Templates.
 */
public class ObjetoPersistenteUpdate extends ObjetoPersistenteImpl {
    public ObjetoPersistenteUpdate(Object o) {
        object = o;
        accion = Accion.update;
    }
}
