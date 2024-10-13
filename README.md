# SIMULACIÓN TABLERO DE GALTON

# PARTICIPANTES

* Marcos Ruiz Esteban
* Samuel Muñoz Vallejo

## OBJETIVO
El objetivo de este proyecto es simular un tablero de Galton utilizando Java y programación concurrente. 
El tablero de Galton es un dispositivo que ilustra la distribución binomial y la ley de los grandes números. 
En esta simulación, las bolas caen a través de una serie de clavijas y se acumulan en contenedores en la parte inferior, 
formando un histograma que muestra la distribución de las bolas.

# ESTRUCTURA 

* Visualizacion:
Esta clase extiende Application y es responsable de la interfaz gráfica de la simulación. Se encarga de inicializar la ventana de JavaFX, dibujar el tablero y las bolas, y actualizar el histograma a medida que las bolas caen en los contenedores.  
* Bola:
Esta clase implementa Component y Runnable. Representa una bola que cae a través del tablero. La clase maneja la lógica de movimiento de la bola y actualiza el histograma cuando la bola llega a un contenedor.  
* Tablero:
Esta clase implementa Component y representa el tablero de Galton. Se encarga de dibujar el tablero en la interfaz gráfica.  
* Buffer:
Esta clase utiliza un BlockingQueue para manejar la comunicación entre los productores (fábricas) y el consumidor (ensamblador). Permite agregar y tomar componentes de manera segura en un entorno multihilo.  
* Ensamblador:
Esta clase extiende Thread y se encarga de ensamblar los componentes creados por las fábricas. Toma componentes del Buffer y los agrega a la visualización. También maneja la lógica para asegurarse de que solo se cree un tablero y un número máximo de bolas.  
* Scheduler:
Esta clase coordina la producción de componentes en las fábricas utilizando un algoritmo round-robin. Se asegura de que las fábricas produzcan componentes de manera equilibrada y controla la cantidad total de bolas producidas.  
* Fabrica:
Esta es una clase abstracta que representa una fábrica que puede crear componentes. Las subclases de Fabrica implementan el método crearComponente para producir diferentes tipos de componentes.  
* EstacionTrabajoTablero:
Esta clase extiende Fabrica y se encarga de crear el componente Tablero.  
* EstacionTrabajoBola:
Esta clase extiende Fabrica y se encarga de crear el componente Bola.





Link al repositorio:  https://github.com/Samuu10/GaltonFactory.git
