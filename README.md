# oblig3-gruppe8
oblig3-gruppe8 created by GitHub Classroom


Introduksjon 
Det skal utvikles en Android-app som lar brukeren kjøpe et eller flere innrammede fotografier som 
selges av diverse fotografer/kunstnere. Appen skal bruke, og vise, informasjon fra følgende JSON-API: 
https://jsonplaceholder.typicode.com 
Dette er et nettsted som leverer ulike typer testdata på JSON-format, og som er gratis å bruke. 
Appen skal bruke følgende ressurser: 
• /users 
• /albums 
• /photos 
«Endepunktet» /users (komplett adresse: https://jsonplaceholder.typicode.com/users) returnerer en 
liste med user-objekter. En user har attributter som f.eks. navn, email-adresse, telefonnummer m.m. 
I denne appen bruker vi user i betydningen fotograf/kunstner.  
Endepunktet /albums leverer fotoalbum-objekter. Et album er knyttet til en user. Endepunktet 
/photos gir en liste med alle fotografier fra alle album til alle users. Dersom vi har et photo-objekt og 
ønsker å finne tilhørende fotograf må vi gå via album. I denne appen bruker vi photo i betydningen 
fotografi som skal kunne selges via appen. 
Appen 
Figuren under viser aktuelle skjermbilder og flyt mellom dem. Handlekurven er i utgangspunktet tom 
og fylles etter hvert som bruker velger å legge bilder i handlekurven. Legg merke til ny og oppdatert 
skisse. Denne foretrekkes. Begge varianter vil allikevel bli godkjent. Den nye viser bl.a. mer 
informasjon per valg bilde. Det betyr også at Photo-klassen bør utvides med flere attributter. 
Ny skisse (8. mars 2023) 
 
 
 
 
Opprinnelig skisse: 
 
Brukeren skal kunne velge mellom alle bilder, fra alle album til alle kunstnere. Når brukeren finner et 
bilde av interesse klikker man på dette og får opp et nytt skjermbilde som viser fullversjonen av 
bildet samt valg for rammetype og bildestørrelse.  
Dersom brukeren velger «Legg i handlekurv» kommer hen tilbake til startskjermbildet. 
Startskjermbildet viser til en oppdatert liste med valgte bilder, antall valgte bilder og totalpris på 
valgte bilder. Prisen beregnes vha. en grunnpris (f.eks. 100kr) pluss tillegg for sølv (+50kr) eller 
gullramme (+120kr), medium (+80kr) eller stort (+150kr) bilde. Når aktuelle bilder er valgt kan 
bestillingen sendes. Bestillingen sendes som en ferdig utfylt email, som viser navn på kunstnere og 
bilder, samt totalpris. Mailen sendes til artphotoagent@some.domain (vi tenker oss at dette er en 
eller annen agent, som videreformidler til aktuelle kunstnere). Når mailen er sendt nullstilles 
bestillingen og alle valg. 
Skjermbildet/fragmentet som viser alle bilder skal bruke info. fra /photos. Når bruker klikker på et av 
bildene skal appen navigere til fragmentet som viser detaljer om bildet og muligheten til å legge det i 
handlekurven. Her skal f.eks. kunstnernavn og email vises. Hvert Photo-objekt inneholder en 
albumId. For å få tak i kunsternavn og email-adresse må appen først hente tilhørende album vha. 
/albums/{albumId}. Album-objektet inneholder bla.a. en userId som så kan brukes til å hente aktuell 
kunstern vha. /users/{userId}. 
Man kan nå vise frem navn og email til kunstneren (som tilsvarer en user i APIet som vi bruker). Man 
må med andre ord gjøre to ekstra «oppslag»/nedlastinger for å få tak i kunstnerinformasjon til bildet. 
Dette gjøres typisk fra viewmodel-klassen. 
Forventede teknikker brukt i løsninga: 
• Appen utvikles vha. en Activity med flere fragmenter. 
• Delt ViewModel som holder på valg og priser. 
• Korutiner. 
• Navigation og tilhørende fragmenter og navigasjonsgraf. 
• Toolbar. Tilbakenavigering. 
• ViewModul, LiveData. 
• Data Binding. 
• Retrofit til nedlasting av data. 
• Moshi til json-konvertering. 
• Coil til visning av thumbnails og bilder. 
• RecyclerView til listene i de ulike fragmentene (startskjerm og «vise-alle-bilder»-skjerm). 
• Appen skal fungere uavbrutt selv om brukere roterer skjermen frem/tilbake mens nedlasting 
pågår.  
For å ha oversikt over alle disse elementene bør du minimum ha fullført alle laboppgaver (inkl. teori) 
frem til og med modul 4. 
Noen starttips: 
• Bruk en felles viewmodel-klasse, som deles av alle fragmenter. Herfra gjøres ting som 
nedlasting av data, beregning av totalpris m.m. Viewmodel-klassen bør holde på en liste med 
alle bilder, en liste med valgte bilder m.m. 
• For å kunne konvertere nedlasted JSON til Kotlin-objekter kan du bruke klassene gitt under, 
dvs. ArtPhoto, ArtArtist, ArtAlbum. Du/dere kan fritt endre på klassenavnene. 
• Lag en ApiService som tilbyr (minst) tre funksjoner, som vist under (ArtPhotoApiService). 
Dette gjør det mulig å hente et album basert på albumId og en user basert på userId. 
• Bruk en RecyclerView til å vise alle bildene. Knytt denne til en adapter som arver fra 
ListAdapter og som tar i mot en onItemClicked-lambda for å håndtere klikkehendelser. Se 
foreslått PhotoGridAdapter-under. Her er det også vist hvordan denne kan brukes fra en 
fragment-klasse. 
• Pass på BindingAdapters.kt, som brukt i kodelaboppgavene. Funksjonene i denne fila lager 
egentlig nye/custom-attributter som igjen kan brukes fra viewene i xml-filene. Følgende 
BindingAdapter-funksjon: 
@BindingAdapter("imageUrl") 
fun bindImage(imgView: ImageView, imgUrl: String?) { 
    imgUrl?.let { 
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build() 
        imgView.load(imgUri) { 
            placeholder(R.drawable.loading_animation) 
            error(R.drawable.ic_broken_image) 
        } 
    } 
} 
 
Brukes f.eks. slik i grid_view_item.xml: 
<ImageView 
    android:id="@+id/art_photo" 
    app:imageUrl="@{photo.thumbnailUrl}" 
    android:layout_width="wrap_content" 
    android:layout_height="170dp" 
    android:scaleType="fitXY" 
    android:adjustViewBounds="true" 
    android:padding="2dp" 
    tools:src="@tools:sample/backgrounds/scenic"/> 
 
• Pass på å få med følgende i build.gradle 
// Retrofit, Moshi, Coil 
implementation 'com.squareup.retrofit2:converter-moshi:2.9.0' 
implementation 'com.squareup.moshi:moshi-kotlin:1.13.0' 
implementation "io.coil-kt:coil:2.2.2" 
 
Mulig utvidelse (dersom tid): 
• Lag en ny knapp på startskjermbildet, «Velg kunstner», som åpner en skjerm med en liste 
over kunstnere. Velger man kunstner får man opp alle albumer til kunstneren. Klikker man på 
et album får man opp alle bilder til albumet. 
Diverse start/hjelpekode. 
Modellklasser som kan brukes sammen med gitt JSON-API: 
Merk:Bruk av @Json(name = ...) er egentlig overflødig her siden klassene bruker samme navn på 
medlemmene som navnene som brukes i JSON-koden. Dersom man derimot ønsker andre navn på 
medlemsvariablene må man spesifisere hvilket attributt i JSON-koden medlemmene tilsvarer. 
data class ArtPhoto( 
    @Json(name = "albumId") val albumId: Int=-1, 
    @Json(name = "id") val id: Int=-1, 
    @Json(name = "title") val title: String="undefined", 
    @Json(name = "url") val url: String="undefined", 
    @Json(name = "thumbnailUrl") val thumbnailUrl: String="undefined", 
) 
 
data class ArtArtist( 
    @Json(name = "id") val id: Int=-1, 
    @Json(name = "name") val name: String="undefined", 
    @Json(name = "email") val email: String="undefined", 
    @Json(name = "phone") val phone: String="undefined", 
    @Json(name = "website") val website: String="undefined", 
) 
 
data class ArtAlbum( 
    @Json(name = "userId") val userId: Int = -1, 
    @Json(name = "id") val id: Int = -1, 
    @Json(name = "title") val title: String = "undefined", 
) 
 
Deler av Retrofit-oppsettet: 
Her er tre ulike funksjoner som kan brukes til å laste ned informasjon fra gitt JSON-API. 
interface ArtPhotoApiService { 
    @GET("photos") 
    suspend fun getPhotos(): List<ArtPhoto> 
 
    @GET("albums/{id}") 
    suspend fun getAlbum(@Path("id") id: Int): ArtAlbum 
 
    @GET("users/{id}") 
    suspend fun getArtist(@Path("id") id: Int): ArtArtist 
} 
 
Eksempel på en GridAdapter som kan brukes til å vise alle bilder fra /photos: 
Merk: dette er en effektiv adapterklasse som kan brukes til vises store datamengder. 
 /* 
  onItemClicked er en lamda-funksjon som tar i mot et ArtPhoto-objekt og 
  returnerer Unit (ingenting). Denne kan f.eks. se slik ut: 
  {artPhoto -> 
      sharedViewModel.onArtPhotoSelected(artPhoto) 
      
findNavController().navigate(R.id.action_allPhotosFragment_to_photoFragment
) 
  } 
 */ 
class PhotoGridAdapter(private val onItemClicked: (ArtPhoto) -> Unit): 
ListAdapter<ArtPhoto, PhotoGridAdapter.ArtPhotoViewHolder>(DiffCallback) { 
 
    class ArtPhotoViewHolder(private var binding: 
GridViewItemBinding):RecyclerView.ViewHolder(binding.root) { 
        fun bind(artPhoto: ArtPhoto) { 
            binding.photo = artPhoto 
            binding.executePendingBindings() 
        } 
    } 
 
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): 
PhotoGridAdapter.ArtPhotoViewHolder { 
        return ArtPhotoViewHolder(GridViewItemBinding.inflate( 
            LayoutInflater.from(parent.context))) 
    } 
 
    override fun onBindViewHolder(holder: 
PhotoGridAdapter.ArtPhotoViewHolder, position: Int) { 
        val artPhoto = getItem(position) 
        holder.itemView.setOnClickListener { 
            onItemClicked(artPhoto) 
        } 
        holder.bind(artPhoto) 
    } 
 
    companion object DiffCallback : DiffUtil.ItemCallback<ArtPhoto>() { 
        override fun areItemsTheSame(oldItem: ArtPhoto, newItem: ArtPhoto): 
Boolean { 
            return oldItem.id == newItem.id 
        } 
 
        override fun areContentsTheSame(oldItem: ArtPhoto, newItem: 
ArtPhoto): Boolean { 
            return oldItem.thumbnailUrl == newItem.thumbnailUrl 
        } 
    } 
} 
 
Eksempel på bruk av PhotoGripAdaptr fra en fragment-klasse: 
class AllPhotosFragment : Fragment() { 
   . . .  
override fun onCreateView( 
    inflater: LayoutInflater, container: ViewGroup?, 
    savedInstanceState: Bundle? 
): View? { 
    val fragmentBinding = FragmentAllPhotosBinding.inflate(inflater, 
container, false) 
    binding = fragmentBinding 
    // NB! Sørger for at variablene i xml-fila kan observere endringer: 
    binding.lifecycleOwner = this 
    // Gir data-variabel definert i xml-fila tilgang ViewModel-objekt. 
    binding.viewModel = sharedViewModel 
    // Oppretter et pterodactyl til RecyclerViewen. Gjør også bildene 
RecyclerView klikkbare. 
    val adapter = PhotoGridAdapter({artPhoto -> 
        sharedViewModel.onArtPhotoSelected(artPhoto) 
findNavController().navigate(R.id.action_allPhotosFragment_to_photoFragme
nt) 
    }) 
    binding.photosGrid.adapter = adapter 
 
    return fragmentBinding.root 
} 
   . . . 
