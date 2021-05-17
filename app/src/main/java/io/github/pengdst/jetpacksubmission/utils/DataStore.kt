package io.github.pengdst.jetpacksubmission.utils

import io.github.pengdst.jetpacksubmission.data.models.Movie
import io.github.pengdst.jetpacksubmission.data.models.TvShow

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
object DataStore {

    const val TYPE_MOVIE = "movie"
    const val TYPE_TV_SHOW = "tv_show"

    val movies = listOf(
        Movie(
            id = "MOVIE_1",
            title = "THE DIG",
            imageUrl = "https://www.newdvdreleasedates.com/images/posters/the-dig-2018.jpg",
            releaseDate = "Jan 29, 2021",
            language = "English (United Kingdom)",
            genre = "Drama",
            storyLine = "Ralph Fiennes and Carey Mulligan star in this Netflix original film based on true events and set in 1939. Mulligan plays a wealthy widow who hires an archaeologist (Fiennes) to excavate the burial mounds on her estate, leading to the discovery of a wealth of medieval Anglo-Saxon artifacts.",
        ),
        Movie(
            id = "MOVIE_2",
            title = "The SpongeBob Movie: Sponge on the Run (2021)",
            imageUrl = "https://cdn.traileraddict.com/content/paramount-pictures/sponge-on-run-poster.jpg",
            releaseDate = "Mar 4, 2021",
            language = "English (United Kingdom)",
            genre = "Kids And Family, Adventure, Animation, Comedy",
            storyLine = "The long-running, wildly popular animated series gets another big screen outing as the whole Spongebob Squarepants gang are back for another adventure. This time out, Spongebob and Patrick embark on a rescue mission to recover Gary, who has been taken by Poseidon, and find themselves in the mystical land of Atlantic City."
        ),
        Movie(
            id = "MOVIE_3",
            title = "Malcolm & Marie",
            imageUrl = "https://occ-0-299-300.1.nflxso.net/dnm/api/v6/evlCitJPPCVCry0BZlEFb5-QjKc/AAAABQXfFBK-LuklIED_eT4C7ABpxv7WSe8-FaZ_5TbzLVs70VKiQ1SmdxCzcwDdvd5xHVXFRKVWplCECxAkpfyhiSv4pZWXOfVdE3GJYmsL-ngMcjII6RYG0V5nDVXqkw.jpg",
            releaseDate = "February 5, 2021",
            language = "English (United Kingdom)",
            genre = "Drama",
            storyLine = "If you feel like you’ve been wasting time during quarantine, wait until you hear about writer/director Sam Levinson, who wrote an entire movie script in six days, then called up John David Washington and Zendaya to be in that movie, and then filmed it, all during lockdown. The story follows a filmmaker and his girlfriend as they return from a movie premiere and, over the course of the evening, begin to hash out their relationship."
        ),
        Movie(
            id = "MOVIE_4",
            title = "Judas and the Black Messiah",
            imageUrl = "https://img.reelgood.com/content/movie/edffa556-dcf1-415f-a6ae-bc9afe155c0a/poster-780.jpg",
            releaseDate = "Feb 12, 2021",
            language = "English (United Kingdom)",
            genre = "History, Biography, Drama",
            storyLine = "FBI informant William O'Neal infiltrates the Illinois Black Panther Party and is tasked with keeping tabs on their charismatic leader, Chairman Fred Hampton. A career thief, O'Neal revels in the danger of manipulating both his comrades and his handler, Special Agent Roy Mitchell. Hampton's political prowess grows just as he's falling in love with fellow revolutionary Deborah Johnson. Meanwhile, a battle wages for O'Neal's soul. Will he align with the forces of good? Or subdue Hampton and The Panthers by any means, as FBI Director J. Edgar Hoover commands?"
        ),
        Movie(
            id = "MOVIE_5",
            title = "To All The Boys: Always And Forever",
            imageUrl = "https://image.tmdb.org/t/p/w500/bEOlLKcsAbWxCMUtZL80BxvqkT4.jpg",
            releaseDate = "Feb 12, 2021",
            language = "English (United Kingdom)",
            genre = "Drama, Romance, Comedy",
            storyLine = "Senior year of high school takes center stage as Lara Jean returns from a family trip to Korea and considers her college plans -- with and without Peter."
        ),
        Movie(
            id = "MOVIE_6",
            title = "I Care a Lot",
            imageUrl = "https://resizing.flixster.com/C_n4-iDSsd3FnJVmXPUSthil4B4=/206x305/v2/https://resizing.flixster.com/4-k4CXIwxuqc4RzII8doRKzA3yQ=/ems.ZW1zLXByZC1hc3NldHMvbW92aWVzL2I3ODViYjk4LTE5NDUtNDYxNi05YTUzLWI2ZmNmYWY0NGU1Zi5qcGc=",
            releaseDate = "Feb 19, 2021",
            language = "English (United Kingdom)",
            genre = "Mystery And Thriller, Comedy",
            storyLine = "Rosamund Pike stars in this comedy as a con woman who takes swindles the elderly out of their money until she comes into contact with a woman who is more cunning than she appears."
        ),
        Movie(
            id = "MOVIE_7",
            title = "NOMADLAND",
            imageUrl = "https://www.themoviedb.org/t/p/original/hDnSdalzjnZoveu8mpdRij8IF1u.jpg",
            releaseDate = "February 19, 2021",
            language = "English (United Kingdom)",
            genre = "Drama",
            storyLine = "A woman embarks on a journey through the American West after losing everything during the recession."
        ),
        Movie(
            id = "MOVIE_8",
            title = "The Father",
            imageUrl = "https://media2.firstshowing.net/firstshowing/img11/TheFatherPostermainimageBig59901.jpg",
            releaseDate = "Feb 26, 2021 ",
            language = "English (United Kingdom)",
            genre = "Drama",
            storyLine = "Anthony (Academy Award Winner, Anthony Hopkins) is 80, mischievous, living defiantly alone and rejecting the carers that his daughter, Anne (Academy Award and Golden Globe Winner, Olivia Colman), encouragingly introduces. Yet help is also becoming a necessity for Anne; she can't make daily visits anymore and Anthony's grip on reality is unraveling. As we experience the ebb and flow of his memory, how much of his own identity and past can Anthony cling to? How does Anne cope as she grieves the loss of her father, while he still lives and breathes before her? THE FATHER warmly embraces real life, through loving reflection upon the vibrant human condition; heart-breaking and uncompromisingly poignant -- a movie that nestles in the truth of our own lives."
        ),
        Movie(
            id = "MOVIE_9",
            title = "TOM & JERRY",
            imageUrl = "https://www.themoviedb.org/t/p/original/ohQLO7hfiVK0ByF0hSEth3IAziI.jpg",
            releaseDate = "Feb 26, 2021",
            language = "English (United Kingdom)",
            genre = "Drama",
            storyLine = "A legendary rivalry reemerges when Jerry moves into New York City's finest hotel on the eve of the wedding of the century, forcing the desperate event planner to hire Tom to get rid of him. As mayhem ensues, the escalating cat-and-mouse battle soon threatens to destroy her career, the wedding, and possibly the hotel itself."
        ),
        Movie(
            id = "MOVIE_10",
            title = "COMING 2 AMERICA",
            imageUrl = "https://icecreamconvos.com/wp-content/uploads/2021/02/C2A2_2021_KeyArt_Vert_4x5_Date_Ensemble_EN_Final_ANDRE.jpg",
            releaseDate = "Mar 5, 2021",
            language = "English (United Kingdom)",
            genre = "Drama",
            storyLine = "Set in the lush and royal country of Zamunda, newly-crowned King Akeem (Eddie Murphy) and his trusted confidante Semmi(Arsenio Hall) embark on an all-new hilarious adventure that has them traversing the globe from their great African nation to the borough of Queens, New York – where it all began."
        ),
        Movie(
            id = "MOVIE_11",
            title = "RAYA AND THE LAST DRAGON",
            imageUrl = "https://www.themoviedb.org/t/p/original/pJWDbagdts9qnhT7joZ0S3HxtZv.jpg",
            releaseDate = "Mar 5, 2021",
            language = "English (United Kingdom)",
            genre = "Drama",
            storyLine = "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when sinister monsters known as the Druun threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, those same monsters have returned and it's up to a lone warrior, Raya, to track down the last dragon in order to finally stop the Druun for good. However, along her journey, she'll learn that it'll take more than dragon magic to save the world--it's going to take trust as well."
        ),
        Movie(
            id = "MOVIE_12",
            title = "ZACK SNYDER'S JUSTICE LEAGUE",
            imageUrl = "https://www.themoviedb.org/t/p/original/eG7Nbhfl5JY3M6SPnUm0tDIRuxQ.jpg",
            releaseDate = "Mar 18, 2021",
            language = "English (United Kingdom)",
            genre = "Drama",
            storyLine = "In ZACK SNYDER'S JUSTICE LEAGUE, determined to ensure Superman's (Henry Cavill) ultimate sacrifice was not in vain, Bruce Wayne (Ben Affleck) aligns forces with Diana Prince (Gal Gadot) with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions. The task proves more difficult than Bruce imagined, as each of the recruits must face the demons of their own pasts to transcend that which has held them back, allowing them to come together, finally forming an unprecedented league of heroes. Now united, Batman (Affleck), Wonder Woman (Gadot), Aquaman (Jason Momoa), Cyborg (Ray Fisher) and The Flash (Ezra Miller) may be too late to save the planet from Steppenwolf, DeSaad and Darkseid and their dreadful intentions."
        ),
        Movie(
            id = "MOVIE_13",
            title = "NOBODY",
            imageUrl = "https://www.8days.sg/blob/13770426/1bd56651c21d4bb79e0b9787fb2d4bf3/nobody-poster-data.jpg",
            releaseDate = "Mar 26, 2021",
            language = "English (United Kingdom)",
            genre = "Drama",
            storyLine = "Emmy winner Bob Odenkirk (Better Call Saul, The Post, Nebraska) stars as Hutch Mansell, an underestimated and overlooked dad and husband, taking life's indignities on the chin and never pushing back. A nobody. When two thieves break into his suburban home one night, Hutch declines to defend himself or his family, hoping to prevent serious violence. His teenage son, Blake (Gage Munroe, The Shack), is disappointed in him and his wife, Becca (Connie Nielsen, Wonder Woman), seems to pull only further away. The aftermath of the incident strikes a match to Hutch's long-simmering rage, triggering dormant instincts and propelling him on a brutal path that will surface dark secrets and lethal skills. In a barrage of fists, gunfire and squealing tires, Hutch must save his family from a dangerous adversary (famed Russian actor Aleksey Serebryakov, Amazon's McMafia)--and ensure that he will never be underestimated as a nobody again."
        ),
    )

    val tvShowList = listOf(
        TvShow(
            id = "TVSHOW_1",
            title = "The Arrow",
            imageUrl = "https://2.bp.blogspot.com/-Q6hvB9BOCS8/VsPUwFl1aBI/AAAAAAAAKlc/88YB9Qb2XXs/s1600/Poster-Art-for-Arrow-Season-2.jpg",
            releaseDate = "2012",
            language = "English",
            genre = "Kejahatan, Drama, Misteri",
            storyLine = "Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow"
        ),
        TvShow(
            id = "TVSHOW_2",
            title = "Doom Patrol",
            imageUrl = "https://image.tmdb.org/t/p/original/imicPOkYP2S437QjbPvRL9zSV4a.jpg",
            releaseDate = "2019",
            language = "English",
            genre = "Sci-fi & Fantasy, Aksi & Petualangan",
            storyLine = "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find."
        ),
        TvShow(
            id = "TVSHOW_3",
            title = "Family Guy",
            imageUrl = "https://image.tmdb.org/t/p/original/q3E71oY6qgAEiw6YZIHDlHSLwer.jpg",
            releaseDate = "1999",
            language = "English",
            genre = "Animasi, Komedi",
            storyLine = "Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film )."
        ),
        TvShow(
            id = "TVSHOW_4",
            title = "The Flash",
            imageUrl = "https://fanart.tv/fanart/tv/279121/tvposter/the-flash-2014-55f8df0e9785c.jpg",
            releaseDate = "2014",
            language = "English",
            genre = "Drama, Sci-fi & Fantasy",
            storyLine = "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash."
        ),
        TvShow(
            id = "TVSHOW_5",
            title = "Gotham",
            imageUrl = "https://mir-s3-cdn-cf.behance.net/project_modules/1400/cd16d519007077.562d357b15a03.jpg",
            releaseDate = "2014",
            language = "English",
            genre = "Drama, Fantasi, Kejahatan",
            storyLine = "Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka."
        ),
        TvShow(
            id = "TVSHOW_6",
            title = "Grey's Anatomy",
            imageUrl = "https://fanart.tv/fanart/tv/73762/tvposter/greys-anatomy-5429623632d98.jpg",
            releaseDate = "2005",
            language = "English",
            genre = "Drama",
            storyLine = "Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle."
        ),
        TvShow(
            id = "TVSHOW_7",
            title = "Hanna",
            imageUrl = "https://fanart.tv/fanart/movies/50456/movieposter/hanna-53a382c0908a7.jpg",
            releaseDate = "2019",
            language = "English",
            genre = "Aksi & Petualangan, Drama",
            storyLine = "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film."
        ),
        TvShow(
            id = "TVSHOW_8",
            title = "Naruto Shippuden",
            imageUrl = "https://posterspy.com/wp-content/uploads/2018/03/poster-1-cc.jpg",
            releaseDate = "2007",
            language = "Japanese",
            genre = "Animasi, Komedi, Drama",
            storyLine = "Naruto Shippuuden adalah kelanjutan dari serial TV animasi asli Naruto. Kisah ini berkisah tentang Uzumaki Naruto yang lebih tua dan sedikit lebih matang dan upayanya untuk menyelamatkan temannya Uchiha Sasuke dari cengkeraman Shinobi seperti ular, Orochimaru. Setelah 2 setengah tahun, Naruto akhirnya kembali ke desanya Konoha, dan mulai mewujudkan ambisinya, meskipun itu tidak akan mudah, karena Ia telah mengumpulkan beberapa musuh (lebih berbahaya), seperti organisasi shinobi. ; Akatsuki."
        ),
        TvShow(
            id = "TVSHOW_9",
            title = "NCIS",
            imageUrl = "https://fanart.tv/fanart/tv/72108/seasonposter/ncis-579c825a3d855.jpg",
            releaseDate = "2003",
            language = "English",
            genre = "Aksi & Petualangan, Kejahatan, Drama",
            storyLine = "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position."
        ),
        TvShow(
            id = "TVSHOW_10",
            title = "The Simpsons",
            imageUrl = "https://i.pinimg.com/736x/c7/b4/b0/c7b4b0c0265a5bc34089e305f507492f.jpg",
            releaseDate = "1989",
            language = "English",
            genre = "Animasi, Komedi, Keluarga, Drama",
            storyLine = "Bertempat di Springfield, kota rata-rata di Amerika, pertunjukan ini berfokus pada kejenakaan dan petualangan sehari-hari keluarga Simpson; Homer, Marge, Bart, Lisa dan Maggie, serta ribuan pemain virtual. Sejak awal, serial ini telah menjadi ikon budaya pop, menarik ratusan selebriti menjadi bintang tamu. Acara ini juga menjadi terkenal karena satirnya yang tak kenal takut terhadap kehidupan politik, media, dan Amerika secara umum.",
        ),
    )
}