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

    private PathWaypoints buildPathGrid() {
        final PathWaypoints waypoints = new PathWaypoints();

        final Node node604 = new Node(604, new Point(200, 400));
        final Node node851 = new Node(851, new Point(450, 400));
        final Node node751 = new Node(751, new Point(450, 300));
        final Node node1004 = new Node(1004, new Point(600, 400));
        final Node node1149 = new Node(1149, new Point(450, 700));
        final Node node1048 = new Node(1048, new Point(350, 700));
        final Node node1251 = new Node(1251, new Point(450, 800));
        final Node node1147 = new Node(1147, new Point(350, 800));
        final Node node1547 = new Node(1547, new Point(450, 1100));
        final Node node1455 = new Node(1455, new Point(350, 1100));
        final Node node1898 = new Node(1898, new Point(450, 1450));
        final Node node1600 = new Node(1600, new Point(150, 1450));
        final Node node2303 = new Node(2303, new Point(850, 1450));
        final Node node2103 = new Node(2103, new Point(650, 1450));
        final Node node1451 = new Node(1451, new Point(0, 1450));
        final Node node1914 = new Node(1914, new Point(150, 1770));
        final Node node2068 = new Node(2068, new Point(299, 1770));
        final Node node2258 = new Node(2258, new Point(490, 1770));
        final Node node2415 = new Node(2415, new Point(650, 1770));
        final Node node2152 = new Node(2152, new Point(150, 2000));
        final Node node2301 = new Node(2301, new Point(300, 2000));
        final Node node2503 = new Node(2503, new Point(500, 2000));
        final Node node2649 = new Node(2649, new Point(650, 2000));
        final Node node2450 = new Node(2450, new Point(150, 2300));
        final Node node2599 = new Node(2599, new Point(300, 2300));
        final Node node2804 = new Node(2804, new Point(505, 2300));
        final Node node2952 = new Node(2952, new Point(650, 2300));
        final Node node2699 = new Node(2699, new Point(150, 2550));
        final Node node2853 = new Node(2853, new Point(300, 2550));
        final Node node3052 = new Node(3052, new Point(500, 2550));
        final Node node3202 = new Node(3202, new Point(650, 2550));
        final Node node2601 = new Node(2601, new Point(850, 1750));
        final Node node2851 = new Node(2851, new Point(850, 2000));
        final Node node3099 = new Node(3099, new Point(850, 2250));
        final Node node3602 = new Node(3602, new Point(850, 2750));
        final Node node3401 = new Node(3401, new Point(650, 2750));
        final Node node2893 = new Node(2893, new Point(150, 2750));
        final Node node1449 = new Node(1449, new Point(-152, 1601));
        final Node node1247 = new Node(1247, new Point(-352, 1599));
        final Node node2651 = new Node(2651, new Point(-99, 2750));
        final Node node2201 = new Node(2201, new Point(-99, 2300));
        final Node node3298 = new Node(3298, new Point(425, 2873));
        final Node node3403 = new Node(3403, new Point(421, 2982));
        final Node node2855 = new Node(2855, new Point(-137, 2992));

        node1048.addConnectedNode(node1149, 1);
        node851.addConnectedNode(node1149, 1);
        node1004.addConnectedNode(node851, 1);
        node751.addConnectedNode(node851, 1);
        node604.addConnectedNode(node851, 1);
        node1147.addConnectedNode(node1251, 1);
        node1149.addConnectedNode(node1251, 1);
        node1455.addConnectedNode(node1547, 1);
        node1547.addConnectedNode(node1251, 1);
        node1547.addConnectedNode(node1898, 1);
        node1451.addConnectedNode(node1600, 1);
        node1600.addConnectedNode(node1898, 1);
        node1898.addConnectedNode(node2103, 1);
        node2103.addConnectedNode(node2303, 1);
        node2103.addConnectedNode(node2415, 1);
        node2415.addConnectedNode(node2258, 2);
        node2258.addConnectedNode(node2068, 2);
        node2068.addConnectedNode(node1914, 2);
        node1914.addConnectedNode(node1600, 1);
        node2853.addConnectedNode(node2699, 1);
        node2699.addConnectedNode(node2450, 1);
        node2450.addConnectedNode(node2152, 2);
        node2152.addConnectedNode(node2301, 2);
        node2301.addConnectedNode(node2503, 2);
        node2503.addConnectedNode(node2649, 2);
        node2649.addConnectedNode(node2952, 1);
        node2952.addConnectedNode(node3202, 2);
        node3202.addConnectedNode(node3052, 2);
        node3052.addConnectedNode(node2853, 2);
        node2450.addConnectedNode(node2599, 2);
        node2599.addConnectedNode(node2804, 1);
        node2804.addConnectedNode(node2952, 1);
        node1914.addConnectedNode(node2152, 1);
        node2415.addConnectedNode(node2649, 1);
        node2893.addConnectedNode(node2699, 1);
        node2893.addConnectedNode(node3401, 2);
        node3401.addConnectedNode(node3602, 2);
        node3202.addConnectedNode(node3401, 2);
        node3602.addConnectedNode(node3099, 2);
        node2851.addConnectedNode(node3099, 1);
        node2601.addConnectedNode(node2851, 1);
        node2303.addConnectedNode(node2601, 1);
        node1247.addConnectedNode(node1449, 1);
        node1449.addConnectedNode(node1451, 1);
        node2201.addConnectedNode(node2651, 1);
        node2651.addConnectedNode(node2893, 1);
        node3298.addConnectedNode(node3403, 1);
        node3298.addConnectedNode(node3401, 1);
        node3298.addConnectedNode(node2893, 1);
        node2855.addConnectedNode(node2651, 1);

        waypoints.add(node604);
        waypoints.add(node851);
        waypoints.add(node751);
        waypoints.add(node1004);
        waypoints.add(node1149);
        waypoints.add(node1048);
        waypoints.add(node1251);
        waypoints.add(node1147);
        waypoints.add(node1547);
        waypoints.add(node1455);
        waypoints.add(node1898);
        waypoints.add(node1600);
        waypoints.add(node2303);
        waypoints.add(node2103);
        waypoints.add(node1451);
        waypoints.add(node1914);
        waypoints.add(node2068);
        waypoints.add(node2258);
        waypoints.add(node2415);
        waypoints.add(node2152);
        waypoints.add(node2301);
        waypoints.add(node2503);
        waypoints.add(node2649);
        waypoints.add(node2450);
        waypoints.add(node2599);
        waypoints.add(node2804);
        waypoints.add(node2952);
        waypoints.add(node2699);
        waypoints.add(node2853);
        waypoints.add(node3052);
        waypoints.add(node3202);
        waypoints.add(node2601);
        waypoints.add(node2851);
        waypoints.add(node3099);
        waypoints.add(node3602);
        waypoints.add(node3401);
        waypoints.add(node2893);
        waypoints.add(node1449);
        waypoints.add(node1247);
        waypoints.add(node2651);
        waypoints.add(node2201);
        waypoints.add(node3298);
        waypoints.add(node3403);
        waypoints.add(node2855);

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
        table.linkToNode(751);
        room.addComponent(table);
        groundFloor.addRoom(room);
        // Muurimaa
        room = RoomCreator.customRoom("Muurimaa", new Point(465, 0), new Point(0, 0), new Point(315, 0), new Point(315, 450), new Point(45, 450), new Point(45,
                150), new Point(0, 60));
        room.addComponent(new Door(Direction.UP_RIGHT, new Point(45, 445), 80));

        table = new Table("Henri Muurimaa", new Point(165, 100));
        table.setSize(150, 50);
        table.linkToNode(1004);
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

        room = RoomCreator.customRoom("Sales", new Point(-450, 1890), new Point(0, 0), new Point(450, 0), new Point(450, 300), new Point(0, 300));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(245, 300), 80));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Design", new Point(0, 1890), new Point(0, 0), new Point(405, 0), new Point(405, 560), new Point(0, 560));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(0, 390), 80));
        secondFloor.addRoom(room);

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

        room = RoomCreator.customRoom("hallway", new Point(1630, 2750), new Point(0, 0), new Point(200, 0), new Point(200, -600), new Point(250, -600),
                new Point(250, 0), new Point(300, 0), new Point(400, 0), new Point(400, 100), new Point(250, 100), new Point(250, 375), new Point(0, 375));
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

        secondFloor.addComponent(buildSecondFloorPoints());

        return secondFloor;
    }

    private void addMainHallTables(final Room room) {
        // Right-top-cornet
        Table table = new Table("", new Point(1475, 3));
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("Tomi Virkki", new Point(1475, 295));
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("", new Point(1475, 360));
        table.setSize(200, 50);
        room.addComponent(table);

        // Center-aisle-left
        table = new Table("Johan Ånäs", new Point(490, 200));
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("Johannes Tuikkala", new Point(490, 450));
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("", new Point(490, 665));
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("", new Point(490, 915));
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("Henrik Paul", new Point(490, 1130));
        table.setSize(50, 200);
        room.addComponent(table);

        // Center-aisle-middle-left
        table = new Table("", new Point(640, 200));
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("Jonas Granvik", new Point(640, 735));
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("Matti Vesa", new Point(640, 790));
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("Anna Koskinen", new Point(640, 1280));
        table.setSize(200, 50);
        room.addComponent(table);

        // Center-aisle-middle-right
        table = new Table("", new Point(845, 200));
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("", new Point(845, 735));
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("", new Point(845, 790));
        table.setSize(200, 50);
        room.addComponent(table);

        table = new Table("", new Point(845, 1280));
        table.setSize(200, 50);
        room.addComponent(table);

        // Center-aisle-Right
        table = new Table("Joonas Lehtinen", new Point(1140, 200));
        table.setSize(50, 200);
        table.linkToNode(2415);
        room.addComponent(table);

        table = new Table("Jurka Rahikkala", new Point(1140, 450));
        table.setSize(50, 200);
        table.linkToNode(2415);
        room.addComponent(table);

        table = new Table("", new Point(1140, 665));
        table.setSize(50, 200);

        room.addComponent(table);
        table = new Table("Haijan Wang", new Point(1140, 915));
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("Risto Yrjänä", new Point(1140, 1130));
        table.setSize(50, 200);
        room.addComponent(table);

        // Left-top-corner
        table = new Table("Kim Leppänen", new Point(125, 3));
        table.setSize(50, 200);
        table.linkToNode(1247);
        table.setImageUrl("https://lh4.googleusercontent.com/-2R6G15_tu4Y/AAAAAAAAAAI/AAAAAAAAAAA/mIxIsE5vC5Y/s24-c-k-no/photo.jpg");
        room.addComponent(table);

        table = new Table("", new Point(125, 320));
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("", new Point(180, 3));
        table.setSize(50, 200);
        room.addComponent(table);

        table = new Table("", new Point(180, 340));
        table.setSize(200, 50);
        room.addComponent(table);
    }

    private PathWaypoints buildSecondFloorPoints() {
        final PathWaypoints waypoints = new PathWaypoints();

        final Node node1913 = new Node(1913, new Point(-244, 2155));
        final Node node2006 = new Node(2006, new Point(-247, 2250));
        final Node node2287 = new Node(2287, new Point(41, 2250));
        final Node node1895 = new Node(1895, new Point(-359, 2250));
        final Node node1448 = new Node(1448, new Point(350, 1099));
        final Node node1150 = new Node(1150, new Point(350, 801));
        final Node node1050 = new Node(1050, new Point(251, 799));
        final Node node1043 = new Node(1043, new Point(350, 695));
        final Node node957 = new Node(957, new Point(258, 699));
        final Node node874 = new Node(874, new Point(350, 522));
        final Node node971 = new Node(971, new Point(449, 522));
        final Node node1276 = new Node(1276, new Point(750, 526));
        final Node node1408 = new Node(1408, new Point(755, 653));
        final Node node724 = new Node(724, new Point(357, 367));
        final Node node813 = new Node(813, new Point(451, 362));

        node2287.addConnectedNode(node2006, 1);
        node2006.addConnectedNode(node1913, 1);
        node1895.addConnectedNode(node2006, 1);
        node1408.addConnectedNode(node1276, 1);
        node1276.addConnectedNode(node971, 1);
        node971.addConnectedNode(node874, 1);
        node874.addConnectedNode(node1043, 1);
        node1043.addConnectedNode(node957, 1);
        node1050.addConnectedNode(node1150, 1);
        node1043.addConnectedNode(node1150, 1);
        node1150.addConnectedNode(node1448, 1);
        node874.addConnectedNode(node724, 1);
        node971.addConnectedNode(node813, 1);

        waypoints.add(node1913);
        waypoints.add(node2006);
        waypoints.add(node2287);
        waypoints.add(node1895);
        waypoints.add(node1448);
        waypoints.add(node1150);
        waypoints.add(node1050);
        waypoints.add(node1043);
        waypoints.add(node957);
        waypoints.add(node874);
        waypoints.add(node971);
        waypoints.add(node1276);
        waypoints.add(node1408);
        waypoints.add(node724);
        waypoints.add(node813);

        return waypoints;
    }
}
