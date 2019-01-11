RappiAndroidTestTomasCanosa
Cosas que me hubiera gustado agregar:
CollapseBar con el logo de Rappi en el Home, lo tenia pero no llegue a arreglar pequeños bugs y lo tuve que sacar :(.
CollapseBar en el detalle con toda la info dentro de la Bar y las similares afuera, al estilo spotify con el nombre de la playlist y las canciones de la playlist.
Arreglo del Viewpager con el GridView que de vez en cuando se autoscrollea para arriba dios sabe por que. No lo pude sacar porque si ponia un LinearRecyclerView no me gustaba como quedaba esteticamente.
Implementar los buscadores que era una pabada pero no llegue porque se me complico leer los datos de la persistencia offline de datos.

Tipos de capas:

Capa de presentación:
HomeFragment, MainActivity, MovieDetailActivity, MovieDetailFragment.

Capa de negocio:
DataToMovieAdapter, MovieToFragmentAdapter, CubeTransformer, ResultListener, MoviesRecyclerViewSetter, Movie, MoviesContainer, DataAsMovieController.

Capa de datos:
MovieService, TMDBMovieDAO, MovieRoomDatabase, RoomMovieDAO.


+Responsabiidades de cada clase:

DataAsMovieController:
Deduce de donde proviene la informacion para recrear el objeto Movie: de la API o de base de datos interna.

MovieService:
Tiene los metodos para pedir informacion a la API.

TMDBMovieDAO:
Conecta la API a la aplicacion y utiliza el MovieService para obtener la informacion necesaria.

MovieRoomDatabase:
Genera la base de datos interna en el telefono para cachear la info utilizada en persistencia offline.

RoomMovieDAO:
Contiene los metodos para lecto-escritura de datos cacheados.

Movie:
POJO utilizado tanto parsear los datos de la API como de ROOM.

MoviesContainer:
Contiene la lista de resultados proveniente de la API.

CubeTransformer:
Animación del ViewPager. Cubo giratorio donde cada cara es un detalle de una pelicula.

MoviesRecyclerViewSetter:
Obtiene los datos necesarios para crear las Movies y los setea en un RecyclerView deseado.

ResultListener:
Necesario para recibir datos asincronicamnte.

DataToMovieAdapter:
Convierte la data obtenida a un objeto Movie.

MovieToFragmentAdapter:
Convierte un objeto Movie a Fragment para ser utilizado en el ViewPager.

HomeFragment:
Recopila los datos necesarios y los muestra como pantalla principal de la aplicación.

MainActivity:
Contexto donde el HomeFragment se muestra. Pasa la informacion necesaria del HomeFragment al MovieDetailActivity.

MovieDetailActivity:
Contexto donde el DetailFragment se muestra. recibe la informacion del MainActivity y se la pasa al MovieDetailFragment.

MovieDetailFragment:
Contiene el ViewPager donde se muestra el detalle de las Movies.


+En qué consiste el principio de responsabilidad única? Cuál es su propósito?
Forma parte del concepto de S.O.L.I.D, siendo representado por la S(Single Responsibility Principle). Basicamente dictamina que en la buena practica, al momento de crear una entidad, se le implique una sola responsabilidad, un solo fin.
Personalmente, creo que esto es bueno ya que minimiza la propagación de los errores ya que encapsula y aisla cada funcionalidad.

+Qué características tiene, según su opinión, un “buen” código o código limpio?
Un codigo limpio debe ser altamente legible y comprendible, utilizando nombres lo mas descriptivos posibles.
Debe acoger las convenciones y conceptos pre-establecidos, utilizados para diagramar "las buenas practicas". Convencion de nombres de archivos, escalabilidad, reusabilidad, etc.

