/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import javax.persistence.Entity;

/**
 *
 * @author Manuel
 */
@Entity
public class ComponenteAereador extends ComponenteConservante {
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComponenteAereador)) {
            return false;
        }
        ComponenteAereador other = (ComponenteAereador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ComponenteAereador[id=" + id + "]";
    }

}
