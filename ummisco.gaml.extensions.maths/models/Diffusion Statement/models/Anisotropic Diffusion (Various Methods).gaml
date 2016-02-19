/**
* Name: Anisotropic diffusion with several computation method
* Author: Benoit Gaudou
* Description: This model is used to show two different computation methods to use diffusion : with the dot product method and with the convolution method. 
*     The cell at the center of the grid emit a pheromon at each step, which is spread through the grid thanks to the diffusion mechanism, using a particular matrix of diffusion. 
* Tags: Diffusion, Matrix, Elevation
*/

model diffusion

global{
	int taille <- 51;
  	geometry shape <- envelope(square(taille) * 10);
  	cells_dot selected_cells_dot;
  	cells_convol selected_cells_convol;
  	// Declare the anisotropic matrix (diffuse to the left-upper direction)
  	matrix<float> math_diff <- matrix([
									[2/9,2/9,1/9],
									[2/9,1/9,0.0],
									[1/9,0.0,0.0]]);

	// Initialize the emiter cell as the cell at the center of the word
	init {
		selected_cells_dot <- location as cells_dot;
  		selected_cells_convol <- location as cells_convol;
	}
	reflex new_Value {
		ask(selected_cells_dot){
			phero <- 1.0;
		}
		ask(selected_cells_convol){
			phero <- 1.0;
		}		
	}

	reflex diff {
		// Declare a diffusion on the grid "cells_dot" (with a dot product computation) and on "cells_convol" (with a convol computation). 
		// The value of the diffusion will be store in the new variable "phero" of the cell.
		diffuse var: phero on: cells_dot matrix: math_diff method: "dot_product";	
		diffuse var: phero on: cells_convol matrix: math_diff method: "convolution";			
	}
}


grid cells_dot height: taille width: taille {
	// "phero" is the variable storing the value of the diffusion
	float phero  <- 0.0;
	// The color of the cell is linked to the value of "phero".
	rgb color <- hsb(phero,1.0,1.0) update: hsb(phero,1.0,1.0);
	// Update the "grid_value", which will be used for the elevation of the cell
	float grid_value update: phero * 100;
} 

grid cells_convol height: taille width: taille {
	// "phero" is the variable storing the value of the diffusion
	float phero  <- 0.0;
	// The color of the cell is linked to the value of "phero".
	rgb color <- hsb(phero,1.0,1.0) update: hsb(phero,1.0,1.0);
	// Update the "grid_value", which will be used for the elevation of the cell
	float grid_value update: phero * 100;
} 


experiment diffusion type: gui {
	output {
		display dot type: opengl {
			// Display the grid with elevation
			grid cells_dot elevation: true triangulation: true;
		}
		display convol type: opengl {
			// Display the grid with elevation
			grid cells_convol elevation: true triangulation: true;
		}
	}
}
