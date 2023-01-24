package principal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestorArbolApp {
	private static final String HOST= "localhost";
	private static final String BBDD= "arboles";
	private static final String USERNAME="root";
	private static final String PASSWORD="";
	
	public static void run() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner (System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection ("jdbc:mysql://"+HOST+ "/"+BBDD,USERNAME,PASSWORD);
			
			PreparedStatement preparedSt;
			Scanner teclado = new Scanner(System.in);
			final int INSERTAR_ARBOL = 1;
			final int ELIMINAR_ARBOL = 2;
			final int MODIFICAR_ARBOL = 3;
			final int VISUALIZAR_ARBOLES = 4;
			final int SALIR = 0;
			int opcion_menu;

			do {
				System.out.println("------MENU-------");
				System.out.println(INSERTAR_ARBOL + ". Insertar arbol");
				System.out.println(ELIMINAR_ARBOL + ". Eliminar arbol");
				System.out.println(MODIFICAR_ARBOL + ". Modificar arbol");
				System.out.println(VISUALIZAR_ARBOLES + ". Visualizar arboles");
				System.out.println(SALIR + ". Salir");
				System.out.println("*******************");
				System.out.println("Elije una de las opciones");
				opcion_menu = teclado.nextInt();

				switch (opcion_menu) {
				
				case INSERTAR_ARBOL:
					String nombreComun;
					String nombreCientifico;
					String habitat;
					int altura;
					String origen;
					
					System.out.println("Introduce el nombre comun del arbol");
				    nombreComun= scan.nextLine();
				    System.out.println("Introduce el nombre cientifico del arbol");
				    nombreCientifico= scan.nextLine();
				    System.out.println("Introduce el habitat del arbol");
				    habitat= scan.nextLine();
				    System.out.println("Introduce la altura del arbol");
				    altura= scan.nextInt();
				    System.out.println("Introduce el origen del arbol");
				    scan.nextLine();
				    origen= scan.nextLine();
					
					
					
					//INSERTAR ARBOL
					//INSERT INTO `arboles_euskadi` (, `nombre_comun`, `nombre_cientifico`, `habitat`, `altura`, `origen`) VALUES (, '', '', '', '', '')
					
					
					
					preparedSt = con.prepareStatement("INSERT INTO `arboles_euskadi` "
							+ "(`nombre_comun`, `nombre_cientifico`, `habitat`, `altura`, `origen`) VALUES "
							+ "(?, ?,?,?, ?)");
					preparedSt.setString(1 , nombreComun);
					preparedSt.setString(2 , nombreCientifico);
					preparedSt.setString(3 , habitat);
					preparedSt.setInt(4 , altura);
					preparedSt.setString(5 , origen);
					
					preparedSt.execute();

					break;
					
				case ELIMINAR_ARBOL:
					int arbolEliminar;
					//ELIMINAR ARBOL
					// "DELETE FROM arboles_euskadi WHERE nombre_comun= 'pino' "
					System.out.println("Introduce el id del arbol que quieras eliminar");
					arbolEliminar= scan.nextInt();
					
					preparedSt = con.prepareStatement("DELETE FROM arboles_euskadi WHERE id = ?");
					preparedSt.setInt(1,arbolEliminar);
					preparedSt.execute();
					break;
				case MODIFICAR_ARBOL:
					//modificar arbol
					//UPDATE `arboles_euskadi` SET `nombre_comun` = 'pin' WHERE `arboles_euskadi`.`id` = 1
					int arbolModificar;
					String nuevoNombreCo;
					String nuevoNombreCi;
					String nuevoHabitat;
					int nuevaAltura;
					String nuevoOrigen;
					System.out.println("Introduce el id del arbol que quieras modificar");
					arbolModificar= scan.nextInt();
					scan.nextLine();
					System.out.println("Introduce el nuevo nombre comun");
					nuevoNombreCo=scan.nextLine();
					
					System.out.println("Introduce el nuevo nombre cientifico");
					nuevoNombreCi=scan.nextLine();
					
					System.out.println("Introduce el nuevo habitat");
					nuevoHabitat=scan.nextLine();
					
					System.out.println("Introduce la nueva altura");
					nuevaAltura=scan.nextInt();
					
					System.out.println("Introduce el nuevo origen");
					scan.nextLine();
					nuevoOrigen=scan.nextLine();
					
					
					//UPDATE `arboles_euskadi` SET `nombre_comun` = 'dddd', `nombre_cientifico` = 'jj', `habitat` = 'sss', `origen` = 'gg' WHERE `arboles_euskadi`.`id` = 3

					
					preparedSt = con.prepareStatement("UPDATE arboles_euskadi SET nombre_comun = ?,"
						    + " nombre_cientifico = ?, habitat = ?, altura = ?, origen = ? "
							+ "WHERE arboles_euskadi.id = ?");
					preparedSt.setString(1,nuevoNombreCo );
					preparedSt.setString(2,nuevoNombreCi );
					preparedSt.setString(3,nuevoHabitat );
					preparedSt.setInt(4,nuevaAltura );
					preparedSt.setString(5,nuevoOrigen );
					preparedSt.setInt(6,arbolModificar );
					preparedSt.executeUpdate();
					
					break;
				case VISUALIZAR_ARBOLES:
					String senteciaSelect="SELECT * FROM arboles_euskadi";
					Statement st = con.createStatement();
					ResultSet resultado = st.executeQuery(senteciaSelect);	
					while (resultado.next()) {
						System.out.println(
				    resultado.getInt(1) + "-" +resultado.getString(2)+ " " 
					+resultado.getString(3)+ " " +resultado.getString(4) + " "
					+resultado.getInt(5) + " " +resultado.getString(6)
					);
					}
					
					break;
				case SALIR:

				}
				System.out.println("");
			} while (opcion_menu != SALIR);
			teclado.close();

			
			
		
			
			
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		scan.close();	
			}
	
	

}