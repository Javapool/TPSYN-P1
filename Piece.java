
/* classe abstraite Piece */

public abstract class Piece {
private String nom;
private String couleur;


public Piece (String nom, String couleur)
{
setNom(nom);
setCouleur ( couleur );
}

public String getNom ()
  {
  return nom;
  }

public void setNom (String nom)
  {
  this.nom = nom;
  }

public String getCouleur ()
  {
  return couleur;
  }

public void setCouleur ( String couleur )
  {
  if (( couleur == "noir" ) || ( couleur == "blanc" ))
    this.couleur = couleur;
  }
 /* norme math�matique entre cette Position et une autre */
/** 
*m�thode permettant de calculer la norme math�matique entre deux Position 
*depart  la position de d�part d'une Piece
*arrivee la position d'arrivee d'Une Piece
*la somme des carr�s des distances
*
*/
public double norme (Position depart, Position arrivee)
  {
  return Math.pow((depart.getLigne()-arrivee.getLigne()), 2)+ Math.pow((depart.getColonne() - arrivee.getColonne()),2);
  }
/* m�thode abstraite � impl�menter dans chacune des sous - classes */

public abstract boolean estValide (Position depart, Position arrivee);


}
