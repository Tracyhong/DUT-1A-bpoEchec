package echiquier;

public class Coord {
	 /**
     * Coordonnee sur abscisse
     */
    public int x;
    
    /**
     * Coordonnee sur ordonnee
     */
    public int y;
    
    /**
     * Constructeur pour x et y
     * @param x valeur en abscisse
     * @param y caleur en ordonnee
     */
    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructeur : initialise x et y en 0
     */
    public Coord(){
        this(0,0);
    }
    
    /**
     * Constructeur : initialise x et y sur la meme valeur
     * @param xy valeur pour x et pour y
     */
    public Coord(int xy){
        this(xy, xy);
    }
    public Coord(Coord coord) {
    	this.x=coord.getX();
    	this.y=coord.getY();
    }
    public int getX() {
    	return x;
    }
    public int getY() {
    	return y;
    }    
    /**
     * Setter pour x et y
     * @param x nouvelle valeur de x
     * @param y nouvelle valeur de y
     */

    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }

	@Override
	public String toString() {
		return "Coord [x=" + x + ", y=" + y + "]";
	}
	
}
