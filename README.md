# JAVA

## Compilar
```bash
nano Quiz.java
# Cal modificar l'usuari i contrassenya de la base de dades
javac Quiz.java
```

## Crear JAR
```bash
jar cvmf MANIFEST.MF hday.jar Quiz.class 
```

## Executar 
```bash
java -jar hday.jar
```


# Codi SQL per la base de dades
```SQL

-- Creació de la base de dades
CREATE DATABASE quiz;

-- Us de la base de dades
USE quiz;

--
-- Table structure for table `info`
--

CREATE TABLE `info` (
  `option` varchar(11) NOT NULL,
  `value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `info`
--

INSERT INTO `info` (`option`, `value`) VALUES
('ciutat', 'Londres'),
('despedida', 'Moltes gràcies per apagar les màquines de Café i salvar el món!'),
('introduccio', 'Les màquines de cafè han cobrat vida! I ens ataquen!\nPerò com ens podem defensar d\'aquestes màquines? Doncs treient-les l\'electriciat.\nUn antic fabricant de màquines de cafè, ens ha deixat una sèrie de pistes per trobar la localització del generador principal que fan servir les maleides màquines de cafè.');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int NOT NULL,
  `question` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `answer` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `question`, `answer`) VALUES
(1, 'Abans de res demostra que no ets una màquina de cafè! A casa d\'en Joan hi ha tres germans més: l\'Iker, en Pere i la Maria. Com es diu el quart fill de la mare d\'en Joan?', 'Joan'),
(2, 'L\'ordinador central està obert! amb un post-it amb una contrasenya. Com podem reiniciar la màquina?', 'sudo reboot'),
(3, 'Només podràn obtenir la ubicació del generador principal aquells que tinguin un coneixement profund de la història de les màquines de cafè. Per demostrar-ho, indica l\'any que es va fundar Nespresso', '1986'),
(4, 'Ens vam adonar que alguna cosa no anava bé el dia que va aparèixer un café a la serie Game of Thrones. Molta gent se\'n va adonar, es va fer viral, però la intel·ligència artificial col·lectiva lligada a les màquines de café van eliminar la prova. I per dissimular van obligar a l\'actriu a dir que es tractava de té. Quin és el nom i cognom d\'aquesta actriu?', 'Emilia Clarke'),
(5, 'Ja has trobat el generador central! Llum, foc i ...', 'Destrucció');

-- --------------------------------------------------------

--
-- Table structure for table `results`
--

CREATE TABLE `results` (
  `id` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `solved` int NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Indexes for table `info`
--
ALTER TABLE `info`
  ADD PRIMARY KEY (`option`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`id`),
  ADD KEY `solved` (`solved`);


--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `results`
--
ALTER TABLE `results`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;


--
-- Constraints for table `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `results_ibfk_1` FOREIGN KEY (`solved`) REFERENCES `questions` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;


```
