package echiquier;

public class Case {
	private IPiece contenu;
        
	/**
     * Défini une case qui contient une pièce
     * @param contenu 
     */
	public Case(IPiece contenu){
		this.contenu = contenu;
	}
        
	/**
     * Défini une case vide
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
     * Retourne la pièce 
     * @return 
     */
	public IPiece getPiece(){
		return contenu;
	}
	
    /**
     * Affecte une pièce
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
