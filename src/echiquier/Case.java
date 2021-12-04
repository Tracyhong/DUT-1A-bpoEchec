package echiquier;

public class Case {
	private IPiece contenu;
        
	/**
     * D�fini une case qui contient une pi�ce
     * @param contenu 
     */
	public Case(IPiece contenu){
		this.contenu = contenu;
	}
        
	/**
     * D�fini une case vide
     */
	public Case(){
        this.contenu = null;
	}
        

    public Case(Case c){
        if (c.contenu == null){
            this.contenu = null;
        }
        else{
            this.contenu = c.contenu;
        }
    }

    /**
     * Retourne la pi�ce 
     * @return 
     */
	public IPiece getPiece(){
		return contenu;
	}
	
    /**
     * Affecte une pi�ce
     * @param p piece
     */
	public void setPiece(IPiece p){
		contenu = p;
	}
        
    /**
     * Indique si la case est vide
     * @return 
     */
    public boolean estVide(){
        return (contenu == null);
    }
    
    /**
     * Vide la case
     */
    public void vider(){
        contenu = null;
    }
    
    @Override
    public String toString(){
        if (contenu != null)
            return contenu.toString();
        
        else return "   ";
    }	

}
