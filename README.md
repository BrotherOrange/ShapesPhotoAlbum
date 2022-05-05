
# Shapes Photo Album

## Model - The Shapes Album

### Structure

- **Interface**: `IShapesAlbum`
- **Concrete Class**: `ShapesAlbumModel`
- **Aggregation**
    - **Shape**
        - **Interface**: `IShape`
        - **Abstract Class**: `AbstractShape`
        - **Concrete Class**: `Oval`, `Rectangle`
    - **Snapshot**
        - **Interface**: `ISnapshot`
        - **Concrete Class**: `Snapshot`

### Description

1. The Model of Shapes Album makes sure that the canvas is unique.
2. The Model could make different operations according to the formatted inputs.
3. The Model could create `Oval` or `Rectangle` on the canvas.
4. The Model could save all shapes and modify the attributes of each shape by searching for the name.
   - The Model could **move** each `IShape`.
   - The Model could **resize** each `IShape`.
   - The Model could **recolor** each `IShape`.
   - The Model could **remove** each `IShape`.
5. The Model could take a `Snapshot` of the canvas at any time. Each `Snapshot` contains the basic information of itself and the information of all shapes on canvas at that time.
6. The Model could save as many `Snapshot`s as possible.
7. The Model could search for a specific `Snapshot` by its ID.

## Controller - The Shapes Album Controller

### Structure

- **Interface**: `IShapesAlbumController`
- **Concrete Class**: `ShapesAlbumController`
- **I/O**: `Readable`, `Appendable`

### Description

1. The Controller could read formatted input source by lines.
2. The Controller could throw exceptions when trying to make an illegal operation on model.
3. The Controller could provide formatted input as arguments to the Model.
4. The Controller could tell Model to make different operations according to user's input.
5. The Controller could get different outputs from the View according user's input.
6. The Controller provide the only public function - `run()` to interact with user.

## View - The Shapes Album View

### Graphical View - Swing

#### Structure

- **Interface**: `IShapesAlbumGraphicalView`
- **Concrete Class**: `ShapesAlbumGraphicalView`
- **Aggregation**
  - **GraphicsFrame**: extended from `JFrame`
  - **SnapshotPanel**: extended from `JPanel`

#### Description

1. The Graphical View makes sure that the view is **Singleton**.
2. The Graphical View could print out all `IShape`s in an `ISnapshot` on the canvas at one time.
3. The Graphical View provides 4 buttons of operations:
   - **Prev**: Display the previous `ISnapshot` and make a warning when meeting the first one.
   - **Select**: Print out a `InputMessageBox` for selecting a `ISnapshot` from the list of `ISnapshot`s' IDs to display.
   - **Next**: Display the next `ISnapshot` and make a warning when meeting the last one.
   - **Quit**: Quit the *Shapes Photos Album Application*.
4. The Graphical View could set the size of the `ISnapshot` displaying area according to user's input. (Default Size: `1000 x 1000`)
5. The Graphical View could print out the detailed information of a specific `Snapshot` on the top:
   - **ID**: The ID (*CreatedTimestamp* && *Identification Number*) of the `ISnapshot`.
   - **Description**: The description of the `ISnapshot`. *Invisible when there is no description*

### Web View - HTML & SVG

#### Structure

- **Interface**: `IShapesAlbumWebView`
- **Concrete Class**: `ShapesAlbumWebView`

#### Description

1. The Web View makes sure that the view is **Singleton**.
2. The Web View outputs a file in `HTML` format.
3. The Web View set the size of each `ISnapshot` displaying area as `1000 x 1000` in default.
4. The Web View displays all `ISnapshots` in a list.
5. The Web View draws each `ISnapshots` by using `SVG`.
6. The Web View could print out the detailed information of a specific `Snapshot` on the top:
    - **ID**: The ID (*CreatedTimestamp* && *Identification Number*) of the `ISnapshot`.
    - **Description**: The description of the `ISnapshot`. *Invisible when there is no description*

## Main Function

### Description

The Entry function of running the whole program.

### Arguments

1. `-in $INPUT_FILENAME$`: The *filename* or *path + filename* of the input source filename. *Required*
2. `-out $OUTPUT_HTML_FILENAME$`: The *filename* or *path + filename* of the output HTML filename. *Required for Web View*
3. `-v $VIEW_MODE$` or `-view $VIEW_MODE$`: Set the mode of the View. There are two options: *Required*
   - `graphical`: Uss the **Swing Graphical View**
   - `web`: Use the **HTML & SVG Web View**
4. `$X_MAX_SIZE$ $Y_MAX_SIZE$`: Set the xMax and yMax value of the size of `ISnapshot` displaying area. *Optional for Graphical View*