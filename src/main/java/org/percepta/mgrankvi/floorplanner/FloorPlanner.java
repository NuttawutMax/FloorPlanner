package org.percepta.mgrankvi.floorplanner;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Node;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.door.DoorState.Direction;
import org.percepta.mgrankvi.floorplanner.visual.entity.Door;
import org.percepta.mgrankvi.floorplanner.visual.entity.PointToPointItem;
import org.percepta.mgrankvi.floorplanner.visual.entity.Room;
import org.percepta.mgrankvi.floorplanner.visual.entity.RoomCreator;
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

        final Node node604 = new Node(604, new Point(202, 402));
        waypoints.add(node604);
        final Node node851 = new Node(851, new Point(450, 401));
        waypoints.add(node851);
        final Node node751 = new Node(751, new Point(450, 301));
        waypoints.add(node751);
        final Node node1004 = new Node(1004, new Point(601, 403));
        waypoints.add(node1004);
        final Node node1149 = new Node(1149, new Point(449, 700));
        waypoints.add(node1149);
        final Node node1048 = new Node(1048, new Point(350, 698));
        waypoints.add(node1048);
        node1048.addConnectedNode(node1149, 1);
        node851.addConnectedNode(node1149, 1);
        node1004.addConnectedNode(node851, 1);
        node751.addConnectedNode(node851, 1);
        node604.addConnectedNode(node851, 1);
        final Node node1251 = new Node(1251, new Point(451, 800));
        waypoints.add(node1251);
        final Node node1147 = new Node(1147, new Point(349, 798));
        waypoints.add(node1147);
        node1147.addConnectedNode(node1251, 1);
        node1149.addConnectedNode(node1251, 1);
        final Node node1547 = new Node(1547, new Point(448, 1099));
        waypoints.add(node1547);
        final Node node1455 = new Node(1455, new Point(353, 1102));
        waypoints.add(node1455);
        node1455.addConnectedNode(node1547, 1);
        node1547.addConnectedNode(node1251, 1);
        final Node node1898 = new Node(1898, new Point(449, 1449));
        waypoints.add(node1898);
        node1547.addConnectedNode(node1898, 1);
        final Node node1600 = new Node(1600, new Point(150, 1450));
        waypoints.add(node1600);
        final Node node2303 = new Node(2303, new Point(851, 1452));
        waypoints.add(node2303);
        final Node node2103 = new Node(2103, new Point(651, 1452));
        waypoints.add(node2103);
        final Node node1451 = new Node(1451, new Point(0, 1451));
        waypoints.add(node1451);
        node1451.addConnectedNode(node1600, 1);
        node1600.addConnectedNode(node1898, 1);
        node1898.addConnectedNode(node2103, 1);
        node2103.addConnectedNode(node2303, 1);
        final Node node1914 = new Node(1914, new Point(147, 1767));
        waypoints.add(node1914);
        final Node node2068 = new Node(2068, new Point(299, 1769));
        waypoints.add(node2068);
        final Node node2258 = new Node(2258, new Point(491, 1767));
        waypoints.add(node2258);
        final Node node2415 = new Node(2415, new Point(653, 1762));
        waypoints.add(node2415);
        node2103.addConnectedNode(node2415, 1);
        node2415.addConnectedNode(node2258, 1);
        node2258.addConnectedNode(node2068, 1);
        node2068.addConnectedNode(node1914, 1);
        node1914.addConnectedNode(node1600, 1);
        final Node node2152 = new Node(2152, new Point(150, 2002));
        waypoints.add(node2152);
        final Node node2301 = new Node(2301, new Point(300, 2001));
        waypoints.add(node2301);
        final Node node2503 = new Node(2503, new Point(501, 2002));
        waypoints.add(node2503);
        final Node node2649 = new Node(2649, new Point(648, 2001));
        waypoints.add(node2649);
        final Node node2450 = new Node(2450, new Point(150, 2300));
        waypoints.add(node2450);
        final Node node2599 = new Node(2599, new Point(302, 2297));
        waypoints.add(node2599);
        final Node node2804 = new Node(2804, new Point(504, 2300));
        waypoints.add(node2804);
        final Node node2952 = new Node(2952, new Point(652, 2300));
        waypoints.add(node2952);
        final Node node2699 = new Node(2699, new Point(148, 2551));
        waypoints.add(node2699);
        final Node node2853 = new Node(2853, new Point(303, 2550));
        waypoints.add(node2853);
        final Node node3052 = new Node(3052, new Point(500, 2552));
        waypoints.add(node3052);
        final Node node3202 = new Node(3202, new Point(653, 2549));
        waypoints.add(node3202);
        node2853.addConnectedNode(node2699, 1);
        node2699.addConnectedNode(node2450, 1);
        node2450.addConnectedNode(node2152, 1);
        node2152.addConnectedNode(node2301, 1);
        node2301.addConnectedNode(node2503, 1);
        node2503.addConnectedNode(node2649, 1);
        node2649.addConnectedNode(node2952, 1);
        node2952.addConnectedNode(node3202, 1);
        node3202.addConnectedNode(node3052, 2);
        node3052.addConnectedNode(node2853, 2);
        node2450.addConnectedNode(node2599, 1);
        node2599.addConnectedNode(node2804, 1);
        node2804.addConnectedNode(node2952, 1);
        node1914.addConnectedNode(node2152, 1);
        node2415.addConnectedNode(node2649, 1);
        final Node node2601 = new Node(2601, new Point(850, 1751));
        waypoints.add(node2601);
        final Node node2851 = new Node(2851, new Point(851, 2000));
        waypoints.add(node2851);
        final Node node3099 = new Node(3099, new Point(851, 2248));
        waypoints.add(node3099);
        final Node node3602 = new Node(3602, new Point(851, 2751));
        waypoints.add(node3602);
        final Node node3401 = new Node(3401, new Point(650, 2751));
        waypoints.add(node3401);
        final Node node2893 = new Node(2893, new Point(147, 2746));
        waypoints.add(node2893);
        node2893.addConnectedNode(node2699, 1);
        node2893.addConnectedNode(node3401, 1);
        node3401.addConnectedNode(node3602, 1);
        node3202.addConnectedNode(node3401, 1);
        node3602.addConnectedNode(node3099, 1);
        node2851.addConnectedNode(node3099, 1);
        node2601.addConnectedNode(node2851, 1);
        node2303.addConnectedNode(node2601, 1);

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
        room.addComponent(table);
        groundFloor.addRoom(room);
        // Vappula
        room = RoomCreator.customRoom("Vappula", new Point(270, 0), new Point(45, 0), new Point(195, 0), new Point(195, 60), new Point(240, 150), new Point(
                240, 360), new Point(0, 360), new Point(0, 150), new Point(45, 60));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(230, 360), 70));

        table = new Table("Mikael Vappula", new Point(2, 310));
        table.setSize(150, 50);
        room.addComponent(table);
        groundFloor.addRoom(room);
        // Muurimaa
        room = RoomCreator.customRoom("Muurimaa", new Point(465, 0), new Point(0, 0), new Point(315, 0), new Point(315, 450), new Point(45, 450), new Point(45,
                150), new Point(0, 60));
        room.addComponent(new Door(Direction.UP_RIGHT, new Point(45, 445), 80));

        table = new Table("Henri Muurimaa", new Point(165, 100));
        table.setSize(150, 50);
        room.addComponent(table);
        groundFloor.addRoom(room);
        // GuitarHero
        room = RoomCreator.square(3, "Guitar Hero", new Point(105, 450));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(300, 285), 80));
        groundFloor.addRoom(room);
        // PikkuNeukkari
        room = RoomCreator.square(3, "Pikku Neukkari", new Point(105, 750));
        room.addComponent(new Door(Direction.DOWN_RIGHT, new Point(300, 9), 80));
        groundFloor.addRoom(room);
        // Kitchen
        room = RoomCreator.customRoom("Kitchen", new Point(0, 1050), new Point(0, 15), new Point(105, 15), new Point(105, 0), new Point(405, 0), new Point(405,
                300), new Point(0, 300));
        room.addComponent(new Door(Direction.DOWN_LEFT, new Point(405, 9), 80));
        groundFloor.addRoom(room);
        // Lounge
        room = RoomCreator.customRoom("Lounge", new Point(405, 450), new Point(0, 0), new Point(375, 0), new Point(375, 900), new Point(0, 900));
        room.addComponent(new Door(Direction.DOWN_RIGHT, new Point(15, 0), 80));
        room.addComponent(new Door(Direction.DOWN_RIGHT, new Point(15, 900), 80));
        groundFloor.addRoom(room);

        // MainHall width: 560 height: 520, toilet: w80, h100
        room = RoomCreator.customRoom("Main Hall", new Point(-450, 1350), new Point(0, 0), new Point(1680, 0), new Point(1680, 540), new Point(1440, 540),
                new Point(1440, 840), new Point(1680, 840), new Point(1680, 1560), new Point(0, 1560), new Point(0, 840), new Point(240, 840), new Point(240,
                        540), new Point(0, 540));
        room.addComponent(new Door(Direction.DOWN_RIGHT, new Point(1360, 1560), 80));
        room.addComponent(new Door(Direction.DOWN_LEFT, new Point(1480, 1560), 40));
        addMainHallTables(room);
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

        groundFloor.addComponent(buildPathGrid());
        return groundFloor;
    }

    private Component populateSecondFloor() {
        final Floor secondFloor = new Floor();
        secondFloor.setLevel(1);

        Room room = RoomCreator.customRoom("Vihreä Neukkari", new Point(0, 0), new Point(0, 0), new Point(412, 0), new Point(412, 450), new Point(0, 450));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(407, 450), 80));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Violetti Neukkari", new Point(412, 0), new Point(0, 0), new Point(413, 0), new Point(413, 450), new Point(0, 450));
        room.addComponent(new Door(Direction.UP_RIGHT, new Point(5, 450), 80));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("", new Point(105, 450), new Point(0, 0), new Point(200, 0), new Point(200, 300), new Point(0, 300));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(200, 295), 80));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("", new Point(105, 750), new Point(0, 0), new Point(200, 0), new Point(200, 300), new Point(0, 300));
        room.addComponent(new Door(Direction.DOWN_LEFT, new Point(200, 5), 80));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Merketing", new Point(0, 1050), new Point(0, 15), new Point(105, 15), new Point(105, 0), new Point(405, 0), new Point(
                405, 300), new Point(0, 300));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(400, 0), 80));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Sales", new Point(-450, 1890), new Point(0, 0), new Point(450, 0), new Point(450, 300), new Point(0, 300));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(245, 300), 80));
        secondFloor.addRoom(room);

        room = RoomCreator.customRoom("Design", new Point(0, 1890), new Point(0, 0), new Point(405, 0), new Point(405, 560), new Point(0, 560));
        room.addComponent(new Door(Direction.UP_LEFT, new Point(0, 390), 80));
        secondFloor.addRoom(room);

        final PointToPointItem level = new PointToPointItem(new Point(305, 450), new Point(0, 0), new Point(520, 0), new Point(520, 150), new Point(145, 150),
                new Point(145, 600), new Point(0, 600));
        secondFloor.addComponent(level);

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
        room.addComponent(table);

        table = new Table("Jurka Rahikkala", new Point(1140, 450));
        table.setSize(50, 200);
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
}

// //Tiina
// Room room = RoomType.customRoom("Tiina", new Point(0, 0), new Point(0, 0),
// new Point(105, 0), new Point(105, 20), new Point(90, 50),
// new Point(90, 150), new Point(0, 150));
// room.addComponent(new Door(Direction.UP_LEFT, new Point(90, 145));
// grid.addRoom(room);
// // Vappula
// room = RoomType.customRoom("Vappula", new Point(90, 0), new Point(15, 0), new
// Point(65, 0), new Point(65, 20), new Point(80, 50), new Point(80, 120),
// new Point(0, 120), new Point(0, 50), new Point(15, 20));
// room.addDoor(Direction.UP_LEFT, new Point(75, 120));
// grid.addRoom(room);
// // Muurimaa
// room = RoomType.customRoom("Muurimaa", new Point(155, 0), new Point(0, 0),
// new Point(105, 0), new Point(105, 150), new Point(15, 150),
// new Point(15, 50), new Point(0, 20));
// room.addDoor(Direction.UP_RIGHT, new Point(15, 145));
// grid.addRoom(room);
// // GuitarHero
// room = RoomType.square("Guitar Hero", new Point(35, 150));
// room.addDoor(Direction.UP_LEFT, new Point(100, 95));
// grid.addRoom(room);
// // PikkuNeukkari
// room = RoomType.square("Pikku Neukkari", new Point(35, 250));
// room.addDoor(Direction.DOWN_RIGHT, new Point(100, 3));
// grid.addRoom(room);
// // Kitchen
// room = RoomType.customRoom("Kitchen", new Point(0, 350), new Point(0, 5), new
// Point(35, 5), new Point(35, 0), new Point(135, 0), new Point(135, 100),
// new Point(0, 100));
// room.addDoor(Direction.DOWN_LEFT, new Point(135, 3));
// grid.addRoom(room);
// // Lounge
// room = RoomType.customRoom("Lounge", new Point(135, 150), new Point(0, 0),
// new Point(125, 0), new Point(125, 300), new Point(0, 300));
// room.addDoor(Direction.DOWN_RIGHT, new Point(5, 0));
// room.addDoor(Direction.DOWN_RIGHT, new Point(5, 300));
// grid.addRoom(room);
//
// // MainHall width: 560 height: 520, toilet: w80, h100
// grid.addRoom(RoomType.customRoom("Main Hall", new Point(-150, 450), new
// Point(0, 0), new Point(560, 0), new Point(560, 180), new Point(480, 180),
// new Point(480, 280), new Point(560, 280), new Point(560, 520), new Point(0,
// 520), new Point(0, 280), new Point(80, 280), new Point(80, 180),
// new Point(0, 180)));
//
// // 140, 130
// room = RoomType.customRoom(new Point(-140, 970), new Point(0, 0), new
// Point(140, 0), new Point(140, 130), new Point(0, 130));
// room.addDoor(Direction.DOWN_LEFT, new Point(110, 0));
// grid.addRoom(room);
//
// // MetsäNeukkari
// // room = RoomType.customRoom("Metsä neukkari", new Point(0, 970), new
// // Point(0, 0), new Point(160, 0), new Point(160, 130), new Point(0,
// // 130));
// room = RoomType.customRoom("Metsä neukkari", new Point(160, 970), new
// Point(0, 0), new Point(480, 0), new Point(480, 390), new Point(0, 390));
// // room.addDoor(Direction.UP_LEFT, new Point(140, 0));
// room.addDoor(Direction.UP_LEFT, new Point(460, 0));
// grid.addRoom(room);
//
// // StuffRomm
// room = RoomType.customRoom("Storage", new Point(160, 970), new Point(0, 0),
// new Point(70, 0), new Point(70, 130), new Point(0, 130));
// room.addDoor(Direction.UP_LEFT, new Point(60, 0));
// grid.addRoom(room);
// // ServerRoom
// room = RoomType.customRoom("Server room", new Point(230, 970), new Point(0,
// 0), new Point(50, 0), new Point(50, 130), new Point(0, 130));
// room.addDoor(Direction.UP_RIGHT, new Point(10, 0));
// grid.addRoom(room);
