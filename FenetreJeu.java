//package com.projetSynthese;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FenetreJeu extends JFrame
{
  Echiquier e;        //echiquier
  JLabel [][] tab;    //tableau de JLabels
  
  JPanel jPanel1 = new JPanel();  // panel du haut
  JPanel jPanel2 = new JPanel();  // panel du bas ( grille )
  GridLayout gridLayout1 = new GridLayout();
  
  JButton boutonDebuter = new JButton();
  JTextField champTexte = new JTextField();

  public FenetreJeu()   // constructeur appelle m�thode JBInit
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {

  
    tab = new JLabel[8][8];   // cr�ation du tableau de JLabel
    e = new Echiquier();      // cr�ation de l'�chiquier

    
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(568, 585));
    this.setTitle("Jeu d'Echecs");
    jPanel1.setBounds(new Rectangle(5, 10, 550, 45));
    jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jPanel1.setLayout(null);
    jPanel2.setBounds(new Rectangle(5, 65, 550, 465));
    jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jPanel2.setLayout(gridLayout1);
    gridLayout1.setColumns(8);
    gridLayout1.setRows(8);
    this.getContentPane().add(jPanel2, null);
    jPanel1.add(champTexte, null);
    jPanel1.add(boutonDebuter, null);
    this.getContentPane().add(jPanel1, null);

   
    boutonDebuter.setBounds(new Rectangle(15, 10, 130, 25));
    boutonDebuter.setText("DEBUTER");

   
    champTexte.setBounds(new Rectangle(160, 10, 320, 25));



    // les �couteurs
    GestionnaireEvenement gest = new GestionnaireEvenement ();
    boutonDebuter.addMouseListener(gest);

    
    for ( int i = 0; i <8; i++ )
      {
      for ( int j = 0; j <8 ; j++ )
        {
        tab[i][j] = new JLabel(); // cr�ation du JLabel
        jPanel2.add(tab[i][j]);  // ajouter au Panel
        tab[i][j].setOpaque(true);
        tab[i][j].setHorizontalAlignment(SwingConstants.CENTER);  // pour que les pieces apparaissent au centre de la case
        tab[i][j].addMouseListener(gest);  // ajouter l'�couteur aux sources

        
        if ( (i+j) % 2 == 0 )
          tab[i][j].setBackground(Color.lightGray );  //couleur des cases
        else
          tab[i][j].setBackground(Color.darkGray );
        }
      }


  }
  // main pour pouvoir ex�cuter l'interface graphique
  public static void main ( String [] args )
  {
  FenetreJeu j = new FenetreJeu ();
  j.setVisible(true);
  j.setLocation(100, 130);
  j.setDefaultCloseOperation(EXIT_ON_CLOSE);  // ferme le processus associ�
  }
  
  
  // classe priv�e (interne) pour la gestion d'�v�nements
  private class GestionnaireEvenement extends MouseAdapter
{

  Piece pieceTampon=null;
  ImageIcon iconeTampon=null;
  int ligneClic;
  int colonneClic;
  Position depart, arrivee;
  String couleurControle = "blanc";
  public void mouseClicked(MouseEvent eve)
  {
  if ( eve.getSource() == boutonDebuter )
    {
    e.debuter();
    tab[0][0].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\TB.gif"));
    tab[0][1].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\CB.gif"));
    tab[0][2].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\FB.gif"));
    tab[0][3].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\RB.gif"));
    tab[0][4].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\DB.gif"));
    tab[0][5].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\FB.gif"));
    tab[0][6].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\CB.gif"));
    tab[0][7].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\TB.gif"));
    tab[7][0].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\TN.gif"));
    tab[7][1].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\CN.gif"));
    tab[7][2].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\FN.gif"));
    tab[7][3].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\RN.gif"));
    tab[7][4].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\DN.gif"));
    tab[7][5].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\FN.gif"));
    tab[7][6].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\CN.gif"));
    tab[7][7].setIcon ( new ImageIcon ("C:\\Temp\\Icones\\TN.gif"));

    //Pions
    for ( int i = 0; i <8; i++ )
      {
      tab[1][i].setIcon(new ImageIcon("C:\\Temp\\Icones\\PB.gif"));
      tab[6][i].setIcon(new ImageIcon("C:\\Temp\\Icones\\PN.gif"));
      }
    champTexte.setText(couleurControle);
    }
  	else // donc a clique sur un Label
    {
		//trouver lequel
		for ( int i = 0; i < 8 ; i++ )
		{
		for ( int j = 0; j<8; j++ )
			{
			if (eve.getSource() == tab[i][j])
			{
			ligneClic = i;
			colonneClic = j;
			}
			}
		}
		champTexte.setText(couleurControle);
			
		//SUIVRE LES �TAPES DU DOCUMENT FOURNI SUR L�A
		if(pieceTampon==null)  // 1er cas :
		{
		//clique sur une case occupee , tampon vide : case Depart
			if(e.getCase(ligneClic, colonneClic).estOccupee(couleurControle))
			{
				//initialiser position depart
				depart.setLigne(ligneClic);
				depart.setColonne(colonneClic);
				//prendre l'icone et la mettre dans le tampon, prendre la piece et la mettre dans le tampon
				iconeTampon=(ImageIcon)tab[ligneClic][colonneClic].getIcon();
				pieceTampon=e.getCase(ligneClic,colonneClic).getPiece();
			}
		//enlever le tampon de la place d'origine
		}
	// 2�me cas : clique sur une case vide ; tampon plein case d'arrivee, pas de pion en diagonale
	else //tampon plein
		{
			if(!e.getCase(ligneClic,colonneClic).estOccupee())//destination vide
			{
			//initialiser position d'arriv�e
				arrivee.setLigne(ligneClic);
				arrivee.setColonne(colonneClic);
				//v�rifier estValide(), exclue les pions en diagonale
				if(pieceTampon.estValide(depart, arrivee))
				{
          //v�rifier cheminPossible()
          if(e.cheminPossible(depart, arrivee))
          {
        //enlever la pi�ce du tampon et la mettre sur l'arriv�e
          e.getCase(arrivee.getLigne(), arrivee.getColonne()).ajouterPiece(pieceTampon);
          //enlever d�finitivement la pi�ce du d�part
          e.getCase(depart.getLigne(), depart.getColonne()).ajouterPiece(null);
          //placer l'icone tampon sur la place d'arriv�e et l'enlever du tampon
          tab[arrivee.getLigne()][arrivee.getColonne()].setIcon(iconeTampon);
          tab[depart.getLigne()][depart.getColonne()].setIcon(null);
          iconeTampon=null;
          pieceTampon=null;
      //� compl�ter
          alterne();//Change la couleur de la pi�ce
          }
				}
			}
			else//destination occupée
			{
				arrivee.setLigne(ligneClic);
				arrivee.setColonne(colonneClic);
				if(!e.getCase(ligneClic, colonneClic).estOccupee(couleurControle)) 
				{
					if(pieceTampon.estValide(depart, arrivee))
					{
						if(e.cheminPossible(depart, arrivee))
						{
							e.getCase(arrivee.getLigne(), arrivee.getColonne()).ajouterPiece(pieceTampon);
							//enlever d�finitivement la pi�ce du d�part
							e.getCase(depart.getLigne(), depart.getColonne()).ajouterPiece(null);
							//placer l'icone tampon sur la place d'arriv�e et l'enlever du tampon
							tab[arrivee.getLigne()][arrivee.getColonne()].setIcon(iconeTampon);
							tab[depart.getLigne()][depart.getColonne()].setIcon(null);
							iconeTampon=null;
							pieceTampon=null;
							alterne();
						}
					}
					else if (pieceTampon.getNom().charAt(0)=='p'&&e.captureParUnPionPossible(depart, arrivee))
					{
						e.getCase(arrivee.getLigne(), arrivee.getColonne()).ajouterPiece(pieceTampon);
						//enlever d�finitivement la pi�ce du d�part
						e.getCase(depart.getLigne(), depart.getColonne()).ajouterPiece(null);
						//placer l'icone tampon sur la place d'arriv�e et l'enlever du tampon
						tab[arrivee.getLigne()][arrivee.getColonne()].setIcon(iconeTampon);
						tab[depart.getLigne()][depart.getColonne()].setIcon(null);
				
						alterne();
					}
				}
      }
      iconeTampon=null;
      pieceTampon=null;

		}
	}	


	  
    // 3�me cas : clique sur une case occupee et tampon plein : case d'arrivee + pion en diagonale
    //� compl�ter
  //else // 4e cas rien a faire tampon vide et case vide
    //this.iconeTampon=null;
	//this.pieceTampon=null;
}
  //Fin m�thode mouseClicked()
  public void alterne ()
  {
  if (couleurControle == "blanc")
    couleurControle = "noir";
  else
    couleurControle = "blanc";
  }

	}//Fin classe interne
}//Fin FenetreJeu









