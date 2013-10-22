package org.percepta.mgrankvi.floorplanner;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Node;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.door.DoorState.Direction;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.Circle;
import org.percepta.mgrankvi.floorplanner.visual.entity.Door;
import org.percepta.mgrankvi.floorplanner.visual.entity.PointToPointItem;
import org.percepta.mgrankvi.floorplanner.visual.entity.Room;
import org.percepta.mgrankvi.floorplanner.visual.entity.RoomCreator;
import org.percepta.mgrankvi.floorplanner.visual.entity.Stair;
import org.percepta.mgrankvi.floorplanner.visual.entity.Table;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class FloorPlanner extends UI {

    @Override
    protected void init(final VaadinRequest request) {
        final CssLayout layout = new CssLayout();
        setContent(layout);

        final Grid grid = new Grid();
        // grid.addRoom(RoomType.square(new Point()));

        grid.addComponent(populateGroundFloor());
        grid.addComponent(populateSecondFloor());

        layout.addComponent(grid);

    }

    private int i = 1;

    private int getNumber() {
        return i++;
    }

    private Node loungeStairNode, mainhallNode, outsideNode;

    private PathWaypoints buildPathGrid() {
        final PathWaypoints waypoints = new PathWaypoints();

        final Node node604 = new Node(604, new Point(202, 402));
        final Node node865 = new Node(865, new Point(463, 402));
        final Node node736 = new Node(736, new Point(460, 276));
        final Node node1013 = new Node(1013, new Point(612, 401));
        final Node node979 = new Node(979, new Point(451, 528));
        final Node node1198 = new Node(1198, new Point(658, 540));
        loungeStairNode = node1198;
        final Node node1756 = new Node(1756, new Point(455, 1301));
        final Node node1901 = new Node(1901, new Point(448, 1453));
        final Node node2103 = new Node(2103, new Point(651, 1452));
        final Node node2400 = new Node(2400, new Point(950, 1450));
        final Node node1599 = new Node(1599, new Point(149, 1450));
        final Node node2602 = new Node(2602, new Point(949, 1653));
        final Node node2773 = new Node(2773, new Point(948, 1825));
        final Node node3752 = new Node(3752, new Point(949, 2803));
        final Node node3418 = new Node(3418, new Point(623, 2795));
        final Node node2925 = new Node(2925, new Point(135, 2790));
        final Node node2679 = new Node(2679, new Point(-115, 2794));
        final Node node2890 = new Node(2890, new Point(-121, 3011));
        final Node node2701 = new Node(2701, new Point(149, 2552));
        final Node node3197 = new Node(3197, new Point(649, 2548));
        final Node node2514 = new Node(2514, new Point(150, 2364));
        final Node node3001 = new Node(3001, new Point(648, 2353));
        final Node node2156 = new Node(2156, new Point(151, 2005));
        final Node node2654 = new Node(2654, new Point(651, 2003));
        final Node node1799 = new Node(1799, new Point(148, 1651));
        final Node node2301 = new Node(2301, new Point(651, 1650));
        final Node node1351 = new Node(1351, new Point(-99, 1450));
        final Node node1698 = new Node(1698, new Point(-100, 1798));
        final Node node1428 = new Node(1428, new Point(-371, 1799));
        final Node node1093 = new Node(1093, new Point(-357, 1450));
        final Node node2188 = new Node(2188, new Point(-116, 2304));
        final Node node2039 = new Node(2039, new Point(-267, 2306));
        mainhallNode = node2039;
        final Node node3947 = new Node(3947, new Point(965, 2982));
        final Node node4450 = new Node(4450, new Point(1471, 2979));
        final Node node4310 = new Node(4310, new Point(1465, 2845));
        final Node node4551 = new Node(4551, new Point(1699, 2852));
        final Node node4648 = new Node(4648, new Point(1699, 2949));
        final Node node4549 = new Node(4549, new Point(1598, 2951));
        outsideNode = node4549;
        //

        node604.addConnectedNode(node865, 1);
        node865.addConnectedNode(node1013, 1);
        node865.addConnectedNode(node736, 1);
        node865.addConnectedNode(node979, 1);
        node979.addConnectedNode(node1198, 1);
        node979.addConnectedNode(node1756, 1);
        node1756.addConnectedNode(node1901, 1);
        node1901.addConnectedNode(node2103, 1);
        node2103.addConnectedNode(node2400, 1);
        node2400.addConnectedNode(node2602, 1);
        node2602.addConnectedNode(node2773, 1);
        node1901.addConnectedNode(node1599, 1);
        node1599.addConnectedNode(node1351, 1);
        node1351.addConnectedNode(node1698, 1);
        node1698.addConnectedNode(node1428, 1);
        node1428.addConnectedNode(node1093, 1);
        node1599.addConnectedNode(node1799, 1);
        node2103.addConnectedNode(node2301, 1);
        node1799.addConnectedNode(node2156, 1);
        node2301.addConnectedNode(node2654, 1);
        node2156.addConnectedNode(node2514, 1);
        node2654.addConnectedNode(node3001, 1);
        node2514.addConnectedNode(node2701, 1);
        node3001.addConnectedNode(node3197, 1);
        node2773.addConnectedNode(node3752, 1);
        node3197.addConnectedNode(node3418, 1);
        node3418.addConnectedNode(node3752, 1);
        node2701.addConnectedNode(node2925, 1);
        node2925.addConnectedNode(node3418, 1);
        node2679.addConnectedNode(node2925, 1);
        node2679.addConnectedNode(node2188, 1);
        node2039.addConnectedNode(node2188, 1);
        node2188.addConnectedNode(node1698, 1);
        node3752.addConnectedNode(node3947, 1);
        node3947.addConnectedNode(node4450, 1);
        node4450.addConnectedNode(node4310, 1);
        node4310.addConnectedNode(node4551, 1);
        node4551.addConnectedNode(node4648, 1);
        node4648.addConnectedNode(node4549, 1);
        node2679.addConnectedNode(node2890, 1);
        node2701.addConnectedNode(node3197, 2);
        node2514.addConnectedNode(node3001, 2);
        node2156.addConnectedNode(node2654, 2);
        node1799.addConnectedNode(node2301, 2);

        waypoints.add(node604);
        waypoints.add(node865);
        waypoints.add(node736);
        waypoints.add(node1013);
        waypoints.add(node979);
        waypoints.add(node1198);
        waypoints.add(node1756);
        waypoints.add(node1901);
        waypoints.add(node2103);
        waypoints.add(node2400);
        waypoints.add(node1599);
        waypoints.add(node2602);
        waypoints.add(node2773);
        waypoints.add(node3752);
        waypoints.add(node3418);
        waypoints.add(node2925);
        waypoints.add(node2679);
        waypoints.add(node2890);
        waypoints.add(node2701);
        waypoints.add(node3197);
        waypoints.add(node2514);
        waypoints.add(node3001);
        waypoints.add(node2156);
        waypoints.add(node2654);
        waypoints.add(node1799);
        waypoints.add(node2301);
        waypoints.add(node1351);
        waypoints.add(node1698);
        waypoints.add(node1428);
        waypoints.add(node1093);
        waypoints.add(node2188);
        waypoints.add(node2039);
        waypoints.add(node3947);
        waypoints.add(node4450);
        waypoints.add(node4310);
        waypoints.add(node4551);
        waypoints.add(node4648);
        waypoints.add(node4549);

        return waypoints;
    }

    private Floor populateGroundFloor() {
        final Floor groundFloor = new Floor();

        // Tiina
        Room room = RoomCreator.customRoom("Tiina", new Point(0, 0), new Point(0, 0), new Point(315, 0), new Point(315, 60), new Point(270, 150), new Point(
                270, 450), new Point(0, 450));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(270, 445), 80));
        Table table = new Table("Tiina Wasberg", new Point(2, 150));
        table.setSize(150, 50);
        table.linkToNode(604);
        room.addComponent(table);
        groundFloor.addRoom(room);
        // Vappula
        room = RoomCreator.customRoom("Vappula", new Point(270, 0), new Point(45, 0), new Point(195, 0), new Point(195, 60), new Point(240, 150), new Point(
                240, 360), new Point(0, 360), new Point(0, 150), new Point(45, 60));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(230, 360), 70));

        table = new Table("Mikael Vappula", new Point(2, 310));
        table.setSize(150, 50);
        table.linkToNode(736);
        room.addComponent(table);
        groundFloor.addRoom(room);
        // Muurimaa
        room = RoomCreator.customRoom("Muurimaa", new Point(465, 0), new Point(0, 0), new Point(315, 0), new Point(315, 450), new Point(45, 450), new Point(45,
                150), new Point(0, 60));
        room.addComponent(new Door(Direction.UP_RIGHT, new Point(45, 445), 80));

        table = new Table("Henri Muurimaa", new Point(165, 100));
        table.setSize(150, 50);
        table.linkToNode(1013);
        table.setImageUrl("https://lh5.googleusercontent.com/-qkwWg6kmP5M/AAAAAAAAAAI/AAAAAAAAAAA/DFvSHP2ouRw/s24-c-k-no/photo.jpg");
        room.addComponent(table);
        groundFloor.addRoom(room);

        // Filler
        final PointToPointItem filler = new PointToPointItem(new Point(270, 360), new Point(0, 0), new Point(240, 0), new Point(240, 90), new Point(0, 90));
        filler.setColor("PEACHPUFF");
        groundFloor.addComponent(filler);

        room = RoomCreator.customRoom("Lounge", new Point(0, 450), new Point(105, 0), new Point(780, 0), new Point(780, 900), new Point(0, 900), new Point(0,
                615), new Point(105, 615));
        room.addComponent(new Door(Direction.DOWN_RIGHT, new Point(420, 0), 80));
        room.addComponent(new Door(Direction.DOWN_RIGHT, new Point(420, 900), 80));
        Stair stair = new Stair("", new Point(625, 150));
        stair.addCircle(new Circle(new Point(0, 0), Math.PI * 1.5, Math.PI, 120));
        stair.setSegmentCircle(20);
        room.addComponent(stair);
        groundFloor.addRoom(room);

        // MainHall width: 560 height: 520, toilet: w80, h100
        room = RoomCreator.customRoom("Main Hall", new Point(-450, 1350), new Point(0, 0), new Point(1680, 0), new Point(1680, 540), new Point(1440, 540),
                new Point(1440, 840), new Point(1680, 840), new Point(1680, 1560), new Point(0, 1560), new Point(0, 840), new Point(240, 840), new Point(240,
                        540), new Point(0, 540));
        room.addComponent(new Door(Direction.DOWN_RIGHT, new Point(1360, 1560), 80));
        room.addComponent(new Door(Direction.DOWN_LEFT, new Point(1480, 1560), 40));
        addMainHallTables(room);
        stair = new Stair("", new Point(120, 980));
        stair.addCircle(new Circle(new Point(0, 0), 0, Math.PI * 2, 120));
        stair.setSegmentCircle(20);
        room.addComponent(stair);

        groundFloor.addRoom(room);

        // 140, 130
        room = RoomCreator.customRoom(new Point(-420, 2910), new Point(0, 0), new Point(420, 0), new Point(420, 390), new Point(0, 390));
        room.addComponent(new Door(Direction.DOWN_LEFT, new Point(330, 0), 80));
        // TODO: ADD 3 tables.

        table = new Table("89", new Point(5, 140));
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Johannes Häyry", new Point(5, 215));
        table.setImageUrl("https://lh5.googleusercontent.com/-ukjWUw7gwEw/AAAAAAAAAAI/AAAAAAAAAAA/7799InohCRw/s24-c-k-no/photo.jpg");
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("86", new Point(275, 235));
        table.setSize(50, 150);
        room.addComponent(table);
        groundFloor.addRoom(room);

        // MetsäNeukkari
        room = RoomCreator.customRoom("Metsä neukkari", new Point(0, 2910), new Point(0, 0), new Point(480, 0), new Point(480, 390), new Point(0, 390));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(460, 0), 80));
        table = new Table();
        table.setSize(200, 200);
        table.setPosition(new Point(100, 70));
        room.addComponent(table);
        groundFloor.addRoom(room);

        // StuffRomm
        room = RoomCreator.customRoom("Storage", new Point(480, 2910), new Point(0, 0), new Point(210, 0), new Point(210, 390), new Point(0, 390));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(180, 0), 80));
        groundFloor.addRoom(room);
        // ServerRoom
        room = RoomCreator.customRoom("Server room", new Point(690, 2910), new Point(0, 0), new Point(150, 0), new Point(150, 390), new Point(0, 390));
        room.addComponent(new Door(Direction.UP_RIGHT, new Point(30, 0), 80));
        groundFloor.addRoom(room);

        room = RoomCreator.customRoom("Hallway", new Point(-470, 1350), new Point(0, 0), new Point(20, 0), new Point(20, 1560), new Point(50, 1560), new Point(
                50, 1950), new Point(1310, 1950), new Point(1310, 1560), new Point(1870, 1560), new Point(1870, 0), new Point(1970, 0), new Point(1990, 1400),
                new Point(2190, 1400), new Point(2190, 800), new Point(2900, 800), new Point(2900, 1500), new Point(2240, 1500), new Point(2240, 1670),
                new Point(2100, 1670), new Point(2100, 1570), new Point(2000, 1570), new Point(2000, 1670), new Point(1870, 1670), new Point(1870, 2050),
                new Point(0, 2050));
        room.setRoomColor("gray");
        groundFloor.addComponent(room);

        room = RoomCreator.square("Stairway", new Point(1530, 2920));
        groundFloor.addComponent(room);
        stair = new Stair("", new Point(0, 50));
        stair.addLines(new Line(new Point(0, 0), new Point(70, 0)), new Line(new Point(10, 0), new Point(10, 50)),
                new Line(new Point(20, 0), new Point(20, 50)), new Line(new Point(30, 0), new Point(30, 50)), new Line(new Point(40, 0), new Point(40, 50)),
                new Line(new Point(50, 0), new Point(50, 50)), new Line(new Point(60, 0), new Point(60, 50)), new Line(new Point(70, 0), new Point(70, 50)));
        room.addComponent(stair);

        groundFloor.addComponent(buildPathGrid());
        return groundFloor;
    }

    private Component populateSecondFloor() {
        final Floor secondFloor = new Floor();
        secondFloor.setLevel(1);

        Room room = RoomCreator.customRoom("Vihreä Neukkari", new Point(0, 0), new Point(0, 0), new Point(412, 0), new Point(412, 450), new Point(0, 450));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(407, 450), 80));
        room.setRoomColor("green");
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Violetti Neukkari", new Point(412, 0), new Point(0, 0), new Point(413, 0), new Point(413, 450), new Point(0, 450));
        room.addComponent(new Door(Direction.UP_RIGHT, new Point(5, 450), 80));
        room.setRoomColor("violet");
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Marketing", new Point(0, 450), new Point(0, 615), new Point(105, 615), new Point(105, 0), new Point(305, 0), new Point(
                305, 600), new Point(405, 600), new Point(405, 900), new Point(0, 900));
        secondFloor.addRoom(room);

        Table table = new Table("-9", new Point(105, 100));
        table.linkToNode(906);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("-8", new Point(105, 155));
        table.linkToNode(1067);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("-7", new Point(105, 355));
        table.linkToNode(1067);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Jouni Koivuviita", new Point(105, 460));
        table.setImageUrl("https://lh3.googleusercontent.com/-55qm1sK3Wy4/AAAAAAAAAAI/AAAAAAAAAAA/LcPgHVOa9u0/s24-c-k-no/photo.jpg");
        table.linkToNode(1261);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("-3", new Point(350, 580));
        table.linkToNode(1261);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("-5", new Point(5, 845));
        table.linkToNode(1368);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("-4", new Point(250, 845));
        table.linkToNode(1368);
        table.setSize(150, 50);
        room.addComponent(table);

        room = RoomCreator.customRoom("Freedom", new Point(-450, 1890), new Point(0, 0), new Point(450, 0), new Point(450, 300), new Point(0, 300));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(245, 300), 80));
        secondFloor.addRoom(room);

        table = new Table("Petri Heinonen", new Point(185, 5));
        table.linkToNode(1834);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("-1", new Point(240, 5));
        table.linkToNode(1834);
        table.setSize(50, 150);
        room.addComponent(table);

        room = RoomCreator.customRoom("Sales", new Point(0, 1890), new Point(0, 0), new Point(405, 0), new Point(405, 560), new Point(0, 560));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(0, 390), 80));
        secondFloor.addRoom(room);

        table = new Table("1", new Point(150, 5));
        table.linkToNode(2286);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("2", new Point(205, 5));
        table.linkToNode(2286);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("Pekka Perälä", new Point(150, 405));
        table.setImageUrl("https://lh5.googleusercontent.com/-oMAFnbvY8EY/AAAAAAAAAAI/AAAAAAAAAAA/YLxukbFcOVg/s24-c-k-no/photo.jpg");
        table.linkToNode(2286);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("4", new Point(205, 405));
        table.linkToNode(2286);
        table.setSize(50, 150);
        room.addComponent(table);

        PointToPointItem level = new PointToPointItem(new Point(305, 450), new Point(0, 0), new Point(320, 0), new Point(320, 150), new Point(145, 150),
                new Point(145, 600), new Point(0, 600));
        level.setColor("GRAY");
        secondFloor.addComponent(level);

        level = new PointToPointItem(new Point(-330, 2190), new Point(0, 0), new Point(330, 0), new Point(330, 260), new Point(120, 260), new Point(120, 140),
                new Point(0, 140));
        level.setColor("GRAY");
        secondFloor.addComponent(level);

        room = RoomCreator.square("Stairway", new Point(1530, 2920));
        secondFloor.addComponent(room);

        Stair stair = new Stair("", new Point(0, 0));
        stair.addLines(new Line(new Point(0, 50), new Point(70, 50)), new Line(new Point(10, 0), new Point(10, 50)), new Line(new Point(20, 0), new Point(20,
                50)), new Line(new Point(30, 0), new Point(30, 50)), new Line(new Point(40, 0), new Point(40, 50)), new Line(new Point(50, 0),
                new Point(50, 50)), new Line(new Point(60, 0), new Point(60, 50)), new Line(new Point(70, 0), new Point(70, 50)));
        room.addComponent(stair);

        room = RoomCreator.customRoom("hallway", new Point(1630, 2650), new Point(0, 0), new Point(200, 0), new Point(200, -1750), new Point(425, -1750),
                new Point(425, -1500), new Point(250, -1500), new Point(250, 0), new Point(774, 0), new Point(774, -500), new Point(974, -500), new Point(974,
                        0), new Point(1374, 0), new Point(1504, 0), new Point(1504, 200), new Point(50, 200), new Point(50, 475), new Point(0, 475));
        room.setRoomColor("gray");
        secondFloor.addComponent(room);

        stair = new Stair("", new Point(625, 600));
        stair.addCircle(new Circle(new Point(0, 0), Math.PI * 1.5, Math.PI, 120));
        stair.setSegmentCircle(20);
        secondFloor.addComponent(stair);

        stair = new Stair("", new Point(-330, 2330));
        stair.addCircle(new Circle(new Point(0, 0), 0, Math.PI * 1.5, 120));
        stair.setSegmentCircle(20);
        secondFloor.addComponent(stair);

        room = RoomCreator.customRoom("Blue meetin groom ", new Point(1880, 1450), new Point(0, 0), new Point(262, 0), new Point(262, 450), new Point(0, 450));
        room.addComponent(new Door(Direction.DOWN_LEFT, new Point(257, 0), 80));
        room.setRoomColor("blue");
        secondFloor.addRoom(room);
        room = RoomCreator
                .customRoom("Green meetin groom 2", new Point(2142, 1450), new Point(0, 0), new Point(262, 0), new Point(262, 450), new Point(0, 450));
        room.addComponent(new Door(Direction.DOWN_RIGHT, new Point(5, 0), 80));
        room.setRoomColor("mossgreen");
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Server room", new Point(1880, 1150), new Point(0, 0), new Point(175, 0), new Point(175, 300), new Point(0, 300));
        room.addComponent(new Door(Direction.DOWN_LEFT, new Point(175, 50), 50));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Kitchen", new Point(2055, 675), new Point(0, 0), new Point(200, 0), new Point(200, 225), new Point(0, 225));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(195, 225), 50));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Toilet", new Point(2255, 675), new Point(0, 0), new Point(200, 0), new Point(200, 113), new Point(0, 113));
        room.addComponent(new Door(Direction.DOWN_RIGHT, new Point(200, 5), 50));
        secondFloor.addRoom(room);
        room = RoomCreator.customRoom("Toilet", new Point(2255, 788), new Point(0, 0), new Point(200, 0), new Point(200, 112), new Point(0, 112));
        room.addComponent(new Door(Direction.UP_RIGHT, new Point(200, 107), 50));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Silent Room", new Point(2604, 2150), new Point(0, 0), new Point(300, 0), new Point(300, 425), new Point(200, 425),
                new Point(200, 500), new Point(0, 500));
        room.addComponent(new Door(Direction.UP_RIGHT, new Point(300, 420), 50));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(300, 420), 50));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Toilet", new Point(2804, 2650), new Point(0, 0), new Point(100, 0), new Point(100, 200), new Point(0, 200));
        room.addComponent(new Door(Direction.UP_RIGHT, new Point(5, 0), 50));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Recreation", new Point(2904, 2150), new Point(0, 0), new Point(300, 0), new Point(300, 700), new Point(0, 700),
                new Point(0, 500), new Point(-100, 500), new Point(-100, 425), new Point(0, 425));
        // room.addComponent(new Door(Direction.UP_RIGHT, new Point(50, 0),
        // 50));
        secondFloor.addRoom(room);

        secondFloor.addRoom(createSecondFloorMainHall());

        room = RoomCreator.customRoom("Closet", new Point(2705, 275), new Point(0, 0), new Point(100, 0), new Point(100, 200), new Point(0, 200));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(0, 190), 50));
        secondFloor.addRoom(room);

        secondFloor.addRoom(createRoomHammer());

        secondFloor.addRoom(createRoomFixer());

        secondFloor.addComponent(buildSecondFloorPoints());

        return secondFloor;
    }

    private Room createRoomHammer() {
        final Room room = RoomCreator.customRoom("Team Hammer", new Point(2555, -125), new Point(0, 0), new Point(650, 0), new Point(650, 650), new Point(250,
                650), new Point(250, 400), new Point(0, 400));
        room.addComponent(new Door(Direction.DOWN_LEFT, new Point(95, 400), 50));

        Table table = new Table("Matti Hosio", new Point(100, 5));
        table.linkToNode(2747);
        table.setImageUrl("https://lh6.googleusercontent.com/-g_JG4V8dxBo/AAAAAAAAAAI/AAAAAAAAAAA/BFmjUrRDmSM/s24-c-k-no/photo.jpg");
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("5", new Point(155, 55));
        table.setSize(50, 100);
        room.addComponent(table);
        table = new Table("Michael Tzukanov", new Point(350, 55));
        table.linkToNode(2949);
        table.setImageUrl("https://lh6.googleusercontent.com/-YZYtH4MMnA8/AAAAAAAAAAI/AAAAAAAAAAA/9dYxG3VaHdM/s24-c-k-no/photo.jpg");
        table.setSize(50, 100);
        room.addComponent(table);
        table = new Table("Mikael Grankvist", new Point(550, 5));
        table.linkToNode(3262);
        table.setImageUrl("https://lh3.googleusercontent.com/-xB4QFSL2fMc/AAAAAAAAAAI/AAAAAAAAAH8/dRUN0bPDt3o/s24-c-k-no/photo.jpg");
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("Thomas Mattsson", new Point(495, 350));
        table.linkToNode(3262);
        table.setImageUrl("https://lh4.googleusercontent.com/-gy7d6awMTqc/AAAAAAAAAAI/AAAAAAAAAAA/4PZ2kHixBrI/s24-c-k-no/photo.jpg");
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Mikolaj Olszewski", new Point(255, 425));
        table.linkToNode(3420);
        table.setImageUrl("https://lh3.googleusercontent.com/-StJG2xriV4M/AAAAAAAAAAI/AAAAAAAAAB8/j4egmaCUPRU/s24-c-k-no/photo.jpg");
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("6", new Point(560, 475));
        table.setSize(50, 100);
        room.addComponent(table);

        return room;
    }

    private Room createRoomFixer() {
        final Room room = RoomCreator.customRoom("Team Fixers", new Point(1780, -125), new Point(0, 0), new Point(775, 0), new Point(775, 500), new Point(225,
                500), new Point(0, 250));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(775, 495), 50));

        Table table = new Table("7", new Point(5, 75));
        table.linkToNode(1922);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Anna Koskinen", new Point(5, 135));
        table.linkToNode(2154);
        table.setImageUrl("https://lh5.googleusercontent.com/-A0C5A7-q2Cg/AAAAAAAAAAI/AAAAAAAAAB0/Fw3THEt9H3c/s24-c-k-no/photo.jpg");
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("8", new Point(300, 75));
        table.linkToNode(1922);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Jonas Granvik", new Point(300, 135));
        table.setImageUrl("https://lh4.googleusercontent.com/-ZLQngo506OI/AAAAAAAAAAI/AAAAAAAABcA/f1KCnDZvK_M/s24-c-k-no/photo.jpg");
        table.linkToNode(2312);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Tapio Aali", new Point(455, 75));
        table.setImageUrl("https://lh6.googleusercontent.com/-L8k_Fqrmob8/AAAAAAAAAAI/AAAAAAAAAAA/ZneK_N2QjlE/s24-c-k-no/photo.jpg");
        table.linkToNode(2443);
        table.setSize(150, 50);
        room.addComponent(table);

        table = new Table("11", new Point(670, 50));
        table.linkToNode(2443);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("12", new Point(670, 270));
        table.linkToNode(2443);
        table.setSize(50, 150);
        room.addComponent(table);

        return room;
    }

    private Room createSecondFloorMainHall() {
        final Room room = RoomCreator.customRoom("Main secon floor hall", new Point(2055, 900), new Point(0, 0), new Point(400, 0), new Point(400, -225),
                new Point(500, -225), new Point(500, -625), new Point(650, -625), new Point(650, -425), new Point(725, -425), new Point(725, -275), new Point(
                        650, -275), new Point(650, -50), new Point(750, -50), new Point(750, -375), new Point(1150, -375), new Point(1150, 1250), new Point(
                        350, 1250), new Point(350, 550), new Point(0, 550));
        // room.addComponent(new Door(Direction.DOWN_LEFT, new Point(175, 50),
        // 50));

        // top right
        Table table = new Table("Rolf Smeds", new Point(895, -290));
        table.setImageUrl("https://lh6.googleusercontent.com/-KH2gjgc7QXM/AAAAAAAAAAI/AAAAAAAAACM/E36FVK5kk7Y/s24-c-k-no/photo.jpg");
        table.linkToNode(3599);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("14", new Point(895, -135));
        table.linkToNode(3599);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("15", new Point(950, -290));
        table.linkToNode(3842);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("16", new Point(950, -135));
        table.linkToNode(3842);
        table.setSize(50, 150);
        room.addComponent(table);

        // Right side
        table = new Table("17", new Point(830, 250));
        table.linkToNode(3885);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Johannes Dahlström", new Point(985, 250));
        table.linkToNode(3885);
        table.setImageUrl("https://lh5.googleusercontent.com/-xYDLkQL1s34/AAAAAAAAAAI/AAAAAAAAAAA/bYJ7JhKlNwI/s24-c-k-no/photo.jpg");
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Juho Nurminen", new Point(830, 305));
        table.setImageUrl("https://lh4.googleusercontent.com/-_TGVegElB2M/AAAAAAAAAAI/AAAAAAAAAAA/OP6fEwGdBng/s24-c-k-no/photo.jpg");
        table.linkToNode(4109);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Matti Tahvonen", new Point(985, 305));
        table.linkToNode(4109);
        table.setImageUrl("https://lh6.googleusercontent.com/-oFaq0p6rlv4/AAAAAAAAAAI/AAAAAAAAAGI/PKMXTinmcVA/s24-c-k-no/photo.jpg");
        table.setSize(150, 50);
        room.addComponent(table);

        table = new Table("19", new Point(830, 555));
        table.linkToNode(4109);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("20", new Point(985, 555));
        table.linkToNode(4109);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("21", new Point(830, 610));
        table.linkToNode(4368);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Henri Sara", new Point(985, 610));
        table.setImageUrl("https://lh5.googleusercontent.com/-_Ekbh7-R3KI/AAAAAAAAAAI/AAAAAAAAAAA/HzgFL2Yz7Pc/s24-c-k-no/photo.jpg");
        table.linkToNode(4368);
        table.setSize(150, 50);
        room.addComponent(table);

        table = new Table("23", new Point(830, 845));
        table.linkToNode(4368);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Artur Signell", new Point(985, 845));
        table.setImageUrl("https://lh5.googleusercontent.com/-pz8oj0nWKyo/AAAAAAAAAAI/AAAAAAAAADI/XJaHbQYLMjc/s24-c-k-no/photo.jpg");
        table.linkToNode(4368);
        table.setSize(150, 50);
        room.addComponent(table);

        // Left-bottom
        table = new Table("25", new Point(355, 500));
        table.linkToNode(4109);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Joonas Lehtinen", new Point(355, 555));
        table.setImageUrl("https://lh3.googleusercontent.com/-n0K5zTgKf0M/AAAAAAAAAAI/AAAAAAAAAAA/JDemsVdbQnQ/s24-c-k-no/photo.jpg");
        table.linkToNode(4368);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("27", new Point(510, 500));
        table.linkToNode(4109);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("28", new Point(510, 555));
        table.linkToNode(4368);
        table.setSize(150, 50);
        room.addComponent(table);

        table = new Table("Henrik Paul", new Point(355, 845));
        table.setImageUrl("https://lh5.googleusercontent.com/-oclvAMrHF5E/AAAAAAAAAAI/AAAAAAAAAPE/B0f94LomOoE/s24-c-k-no/photo.jpg");
        table.linkToNode(4368);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("30", new Point(355, 900));
        table.linkToNode(4739);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Leif Åstrand", new Point(510, 845));
        table.setImageUrl("https://lh4.googleusercontent.com/-FU-9qRxrbcQ/AAAAAAAAAAI/AAAAAAAAAAA/GQCNNwcq0cI/s24-c-k-no/photo.jpg");
        table.linkToNode(4368);
        table.setSize(150, 50);
        room.addComponent(table);
        table = new Table("Mika Murtojärvi", new Point(510, 900));
        table.linkToNode(4739);
        table.setSize(150, 50);
        room.addComponent(table);

        return room;
    }

    private void addMainHallTables(final Room room) {
        // Right-top-cornet
        Table table = new Table("33", new Point(1475, 3));
        table.linkToNode(2400);
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("34", new Point(1475, 295));
        table.linkToNode(2602);
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("Tomi Virtanen", new Point(1475, 360));
        table.setImageUrl("https://lh5.googleusercontent.com/-0Iw96KXaE2A/AAAAAAAAAAI/AAAAAAAAAAA/2zswC_t4DYg/s24-c-k-no/photo.jpg");
        table.linkToNode(2773);
        table.setSize(200, 50);
        room.addComponent(table);

        // Center-aisle-left
        table = new Table("Sebastian Nyholm", new Point(490, 200));
        table.setImageUrl("https://lh3.googleusercontent.com/-eCTMndw5CDY/AAAAAAAAAAI/AAAAAAAAAAA/KsfT0boFV84/s24-c-k-no/photo.jpg");
        table.linkToNode(1799);
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("Petter Holmström", new Point(490, 450));
        table.setImageUrl("https://lh6.googleusercontent.com/-4uF8sUtybY8/AAAAAAAAAAI/AAAAAAAAAAA/KiPgh8PuYUE/s24-c-k-no/photo.jpg");
        table.linkToNode(2156);
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("38", new Point(490, 665));
        table.linkToNode(2514);
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("39", new Point(490, 915));
        table.linkToNode(2514);
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("40", new Point(490, 1130));
        table.linkToNode(2701);
        table.setSize(50, 200);
        room.addComponent(table);

        // Center-aisle-middle-left
        table = new Table("Maciej Przepióra", new Point(640, 200));
        table.setImageUrl("https://lh5.googleusercontent.com/-8obi0i3Ue6s/AAAAAAAAAAI/AAAAAAAAAAA/d4slT0cNH8I/s24-c-k-no/photo.jpg");
        table.linkToNode(1799);
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("42", new Point(640, 735));
        table.linkToNode(2156);
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("Antti Tanhuanpää", new Point(640, 790));
        table.setImageUrl("https://lh5.googleusercontent.com/-op2dCy0gRGs/AAAAAAAAAAI/AAAAAAAAAAA/vexFYBRo2O8/s24-c-k-no/photo.jpg");
        table.linkToNode(2514);
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("Risto Yrjänä", new Point(640, 1280));
        table.linkToNode(2701);
        table.setImageUrl("https://lh5.googleusercontent.com/--Fanu7lbOq8/AAAAAAAAAAI/AAAAAAAABt8/_fWRmH0oGtQ/s24-c-k-no/photo.jpg");
        table.setSize(200, 50);
        room.addComponent(table);

        // Center-aisle-middle-right
        table = new Table("44", new Point(845, 200));
        table.linkToNode(2301);
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("45", new Point(845, 735));
        table.linkToNode(2654);
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("46", new Point(845, 790));
        table.linkToNode(3001);
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("47", new Point(845, 1280));
        table.linkToNode(3197);
        table.setSize(200, 50);
        room.addComponent(table);

        // Center-aisle-Right
        table = new Table("48", new Point(1140, 200));
        table.linkToNode(2301);
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("Jurka Rahikkala", new Point(1140, 450));
        table.linkToNode(2654);
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("Teppo Kurki", new Point(1140, 665));
        table.linkToNode(3001);
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("50", new Point(1140, 915));
        table.linkToNode(3001);
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("Jens Jansson", new Point(1140, 1130));
        table.setImageUrl("https://lh6.googleusercontent.com/-JVtQYjbiMEk/AAAAAAAAAAI/AAAAAAAAAAA/azRDQ2L5PSQ/s24-c-k-no/photo.jpg");
        table.linkToNode(3197);
        table.setSize(50, 200);
        room.addComponent(table);

        // Left-top-corner
        table = new Table("52", new Point(125, 3));
        table.linkToNode(1093);
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("53", new Point(125, 205));
        table.linkToNode(1428);
        table.setSize(50, 200);
        room.addComponent(table);

        // Bottom-Left-corner
        table = new Table("Samuli Penttilä", new Point(200, 1125));
        table.setImageUrl("https://lh6.googleusercontent.com/-x6yRxm9KVQE/AAAAAAAAAAI/AAAAAAAAAAA/krEINGDOb04/s24-c-k-no/photo.jpg");
        table.linkToNode(2188);
        table.setSize(50, 150);
        room.addComponent(table);
        table = new Table("Jonni Nakari", new Point(200, 1280));
        table.setImageUrl("https://lh5.googleusercontent.com/-ZywnCECrsNU/AAAAAAAAAAI/AAAAAAAAAAA/qJx4TlklP-A/s24-c-k-no/photo.jpg");
        table.linkToNode(2679);
        table.setSize(50, 150);
        room.addComponent(table);

    }

    private PathWaypoints buildSecondFloorPoints() {
        final PathWaypoints waypoints = new PathWaypoints();
        //
        final Node node1834 = new Node(1834, new Point(-245, 2079));
        final Node node1999 = new Node(1999, new Point(-250, 2249));
        final Node node2286 = new Node(2286, new Point(43, 2243));
        final Node node1896 = new Node(1896, new Point(-378, 2274));
        final Node node906 = new Node(906, new Point(373, 533));
        final Node node1067 = new Node(1067, new Point(368, 699));
        final Node node1261 = new Node(1261, new Point(360, 901));
        final Node node1368 = new Node(1368, new Point(268, 1100));
        final Node node1182 = new Node(1182, new Point(656, 526));
        final Node node4527 = new Node(4527, new Point(1575, 2952));
        final Node node4608 = new Node(4608, new Point(1655, 2953));
        final Node node4413 = new Node(4413, new Point(1662, 2751));
        final Node node4594 = new Node(4594, new Point(1851, 2743));
        final Node node2953 = new Node(2953, new Point(1860, 1093));
        final Node node3206 = new Node(3206, new Point(2105, 1101));
        final Node node3356 = new Node(3356, new Point(2378, 978));
        final Node node3608 = new Node(3608, new Point(2633, 975));
        final Node node2961 = new Node(2961, new Point(2623, 338));
        final Node node2747 = new Node(2747, new Point(2627, 120));
        final Node node2949 = new Node(2949, new Point(2833, 116));
        final Node node3126 = new Node(3126, new Point(3019, 107));
        final Node node3262 = new Node(3262, new Point(3160, 102));
        final Node node3420 = new Node(3420, new Point(3026, 394));

        final Node node3857 = new Node(3857, new Point(2881, 976));
        final Node node3599 = new Node(3599, new Point(2877, 722));
        final Node node4101 = new Node(4101, new Point(3124, 977));
        final Node node3842 = new Node(3842, new Point(3123, 719));
        final Node node3885 = new Node(3885, new Point(2779, 1106));
        final Node node4109 = new Node(4109, new Point(2786, 1323));
        final Node node4368 = new Node(4368, new Point(2786, 1582));
        final Node node4739 = new Node(4739, new Point(2792, 1947));
        final Node node2774 = new Node(2774, new Point(2433, 341));
        final Node node2636 = new Node(2636, new Point(2304, 332));
        final Node node2443 = new Node(2443, new Point(2297, 146));
        final Node node2312 = new Node(2312, new Point(2154, 158));
        final Node node2154 = new Node(2154, new Point(1998, 156));
        final Node node1922 = new Node(1922, new Point(2002, -80));

        node1834.addConnectedNode(node1999, 1);
        node1999.addConnectedNode(node2286, 1);
        node1999.addConnectedNode(node1896, 1);

        node1368.addConnectedNode(node1261, 1);
        node1261.addConnectedNode(node1067, 1);
        node1067.addConnectedNode(node906, 1);
        node906.addConnectedNode(node1182, 1);

        node4527.addConnectedNode(node4608, 1);
        node4608.addConnectedNode(node4413, 1);
        node4413.addConnectedNode(node4594, 1);
        node4594.addConnectedNode(node2953, 1);
        node2953.addConnectedNode(node3206, 1);
        node3206.addConnectedNode(node3356, 1);
        node3356.addConnectedNode(node3608, 1);
        node3608.addConnectedNode(node2961, 1);
        node2961.addConnectedNode(node2747, 1);
        node2747.addConnectedNode(node2949, 1);
        node2949.addConnectedNode(node3126, 1);
        node3126.addConnectedNode(node3262, 1);
        node3126.addConnectedNode(node3420, 1);

        node3608.addConnectedNode(node3857, 1);
        node3857.addConnectedNode(node3599, 1);
        node3857.addConnectedNode(node4101, 1);
        node4101.addConnectedNode(node3842, 1);
        node3608.addConnectedNode(node3885, 1);
        node3857.addConnectedNode(node3885, 1);
        node3885.addConnectedNode(node4109, 1);
        node4109.addConnectedNode(node4368, 1);
        node4368.addConnectedNode(node4739, 1);
        node2961.addConnectedNode(node2774, 1);
        node2774.addConnectedNode(node2636, 1);
        node2636.addConnectedNode(node2443, 1);
        node2443.addConnectedNode(node2312, 1);
        node2312.addConnectedNode(node2154, 1);
        node2154.addConnectedNode(node1922, 1);

        // 1896 is stairs connection for main hall.
        node1896.addConnectedNode(mainhallNode, 1);
        // 1182 is stairs connection for lounge
        node1182.addConnectedNode(loungeStairNode, 1);
        // 4527 is connection node for outside
        node4527.addConnectedNode(outsideNode, 1);

        // final Node node1913 = new Node(1913, new Point(-244, 2155));
        // final Node node2006 = new Node(2006, new Point(-247, 2250));
        // final Node node2287 = new Node(2287, new Point(41, 2250));
        // final Node node1895 = new Node(1895, new Point(-359, 2250));
        // final Node node1448 = new Node(1448, new Point(350, 1099));
        // final Node node1150 = new Node(1150, new Point(350, 801));
        // final Node node1050 = new Node(1050, new Point(251, 799));
        // final Node node1043 = new Node(1043, new Point(350, 695));
        // final Node node957 = new Node(957, new Point(258, 699));
        // final Node node874 = new Node(874, new Point(350, 522));
        // final Node node971 = new Node(971, new Point(449, 522));
        // final Node node1276 = new Node(1276, new Point(750, 526));
        // final Node node1408 = new Node(1408, new Point(755, 653));
        // final Node node724 = new Node(724, new Point(357, 367));
        // final Node node813 = new Node(813, new Point(451, 362));
        //
        // node2287.addConnectedNode(node2006, 1);
        // node2006.addConnectedNode(node1913, 1);
        // node1895.addConnectedNode(node2006, 1);
        // node1408.addConnectedNode(node1276, 1);
        // node1276.addConnectedNode(node971, 1);
        // node971.addConnectedNode(node874, 1);
        // node874.addConnectedNode(node1043, 1);
        // node1043.addConnectedNode(node957, 1);
        // node1050.addConnectedNode(node1150, 1);
        // node1043.addConnectedNode(node1150, 1);
        // node1150.addConnectedNode(node1448, 1);
        // node874.addConnectedNode(node724, 1);
        // node971.addConnectedNode(node813, 1);
        //
        // waypoints.add(node1913);
        // waypoints.add(node2006);
        // waypoints.add(node2287);
        // waypoints.add(node1895);
        // waypoints.add(node1448);
        // waypoints.add(node1150);
        // waypoints.add(node1050);
        // waypoints.add(node1043);
        // waypoints.add(node957);
        // waypoints.add(node874);
        // waypoints.add(node971);
        // waypoints.add(node1276);
        // waypoints.add(node1408);
        // waypoints.add(node724);
        // waypoints.add(node813);
        //
        waypoints.add(node1834);
        waypoints.add(node1999);
        waypoints.add(node2286);
        waypoints.add(node1896);
        waypoints.add(node906);
        waypoints.add(node1067);
        waypoints.add(node1261);
        waypoints.add(node1368);
        waypoints.add(node1182);
        waypoints.add(node4527);
        waypoints.add(node4608);
        waypoints.add(node4413);
        waypoints.add(node4594);
        waypoints.add(node2953);
        waypoints.add(node3206);
        waypoints.add(node3356);
        waypoints.add(node3608);
        waypoints.add(node2961);
        waypoints.add(node2747);
        waypoints.add(node2949);
        waypoints.add(node3126);
        waypoints.add(node3262);
        waypoints.add(node3420);
        waypoints.add(node3857);
        waypoints.add(node3599);
        waypoints.add(node4101);
        waypoints.add(node3842);
        waypoints.add(node3885);
        waypoints.add(node4109);
        waypoints.add(node4368);
        waypoints.add(node4739);
        waypoints.add(node2774);
        waypoints.add(node2636);
        waypoints.add(node2443);
        waypoints.add(node2312);
        waypoints.add(node2154);
        waypoints.add(node1922);

        return waypoints;
    }
}
