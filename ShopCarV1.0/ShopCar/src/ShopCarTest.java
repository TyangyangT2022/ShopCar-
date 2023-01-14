import java.util.Scanner;

/**
 购物车模拟案例
 */
public class ShopCarTest {
    public static void main(String[] args){
        Goods[] shopCar = new Goods[100];
        while (true) {
            System.out.println("----------选择功能----------");
            System.out.println("1.添加商品");
            System.out.println("2.查询商品");
            System.out.println("3.修改商品");
            System.out.println("4.结算");
            System.out.println("---------------------------");
            System.out.print("选择序号命令:");

            Scanner sc = new Scanner(System.in);
            String command = sc.next();
            System.out.println("\n\n");
            Tips1();

            switch (command) {
                case "1" -> {
                    System.out.println("匹配到命令！");
                    Tips2();
                    addGoods(shopCar, sc);
                }
                case "2" -> {
                    System.out.println("匹配到命令！");
                    Tips2();
                    queryGoods(shopCar);
                }
                case "3" -> {
                    System.out.println("匹配到命令！");
                    Tips2();
                    updateGoods(shopCar,sc);
                }
                case "4" -> {
                    System.out.println("匹配到命令！");
                    Tips2();
                    pay(shopCar);
                }
                default -> {
                    System.out.println("未匹配到命令！！！");
                    Tips2();
                }
            }
        }
    }

    public static void pay(Goods[] shopCar) {
        Goods g;
        double meany = 0;
        for (int i = 0; i < shopCar.length; i++) {
            g = shopCar[i];
            if (g == null) {
                break;
            }
            if (g.buyNumber == 0) {
                g.buyNumber = 1;
            }
            meany += g.price * g.buyNumber;
        }
        System.out.println("----------码上支付----------");
        System.out.println("请支付" + meany + "元。");
        System.out.println("---------------------------\n\n");

        Tips1();
        Tips2();
    }

    public static void updateGoods(Goods[] shopCar,Scanner sc) {
        if (shopCar[0] == null){
            Tips1();
            System.out.println("错误：您因该先添加商品！！！");
            Tips2();
            return;
        }
        System.out.println("----------更改商品----------");
        System.out.print("请输入商品编号:");
        int id = sc.nextInt();
        System.out.println("---------------------------\n\n");

        Tips1();
        System.out.println("查询相关商品中···");
        int location = 0;
        for (int i = 0; i < shopCar.length; i++) {
            Goods g1 = shopCar[i];
            if (g1 == null) {
                System.out.println("未查询到相关商品！");
                break;
            }
            if (g1 != null && g1.id == id){
                System.out.println("查询到相关商品！");
                location = i;
                break;
            }else if(g1.id != id) {
                System.out.println("未查询到商品！");
            }
        }
        Tips2();

        while (true) {
            Tips3(shopCar, location, id);
            System.out.print("选择序号命令:");
            int serial_number = sc.nextInt();
            System.out.println("\n\n");
            Tips1();

            switch (serial_number){
                case 1 -> {
                    System.out.println("匹配到命令！");
                    Tips2();
                    updateGoodsName(shopCar,sc,location,id);
                    return;
                }
                case 2 -> {
                    System.out.println("匹配到命令！");
                    Tips2();
                    updateGoodsPrice(shopCar,sc,location,id);
                    return;
                }
                case 3 -> {
                    System.out.println("匹配到命令！");
                    Tips2();
                    updateGoodsBuyNumber(shopCar,location,id,sc);
                    return;
                }
                case 4 -> {
                    System.out.println("匹配到命令！");
                    Tips2();
                    DeleteGoods(shopCar,location,id,sc);
                    return;
                }
                case 5 -> {
                    System.out.println("匹配到命令!");
                    Tips2();
                    return;
                }
                default -> {
                    System.out.println("未匹配到命令！");
                    Tips2();
                }
            }
        }
    }

    public static void updateGoodsPrice(Goods[] shopCar, Scanner sc, int location,int id) {
        Tips3(shopCar, location, id);
        System.out.print("请输入更改商品价格:");
        shopCar[location].price = sc.nextDouble();
        System.out.println("\n\n");
        Tips1();
        Tips2();
    }

    public static void Tips3(Goods[] shopCar, int location, int id) {
        try {
            System.out.println("----------更改商品----------");
            System.out.println("商品编号:" + id);
            System.out.println("商品名称:" + shopCar[location].name);
            System.out.println("商品价格:" + shopCar[location].price);
            System.out.println("商品数量:" + shopCar[location].buyNumber + "\n");
            System.out.println("1.更改商品名称");
            System.out.println("2.更改商品价格");
            System.out.println("3.更改商品数量");
            System.out.println("4.删除商品");
            System.out.println("5.取消");
            System.out.println("---------------------------");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateGoodsName(Goods[] shopCar, Scanner sc, int location,int id) {
        Tips3(shopCar,location,id);
        System.out.print("请输入更改商品名称:");
        shopCar[location].name = sc.next();
        System.out.println("\n\n");
        Tips1();
        Tips2();
    }

    public static void DeleteGoods(Goods[] shopCar,int location,int id,Scanner sc) {
        Tips1();
        System.out.println("删除编号" + id + "中...");
        Goods g;
        shopCar[location] = null;
        System.out.println("删除编号" + id + "完成！！");
        for (int i = location + 1; i < shopCar.length; i++) {
            g = shopCar[i];
            shopCar[i - 1] = g;
        }
        for (int i = 0; i < shopCar.length; i++) {
            g = shopCar[i];
            if (g == null) {
                break;
            }
            g.id = i + 1;
        }
        Tips2();
    }

    public static void updateGoodsBuyNumber(Goods[] shopCar,int location,int id,Scanner sc) {
        Tips3(shopCar,location,id);
        System.out.print("请输入更改商品数量:");
        shopCar[location].buyNumber = sc.nextInt();
        System.out.println("\n\n");
        Tips1();
        Tips2();
    }


    public static void queryGoods(Goods[] shopCar) {
        Tips1();
        System.out.println("查询商品中···");
        for (int i = 0; i < shopCar.length; i++) {
            Goods G = shopCar[i];
            if (G != null){
                System.out.println("查询到商品！");
                break;
            }else {
                System.out.println("未查询到商品···");
            }
        }
        Tips2();

        System.out.println("----------我的商品----------");
        System.out.println("编号   名称   价格    购买数量");
        for (int i = 0; i < shopCar.length; i++) {
            Goods g = shopCar[i];
            if (g != null){
                System.out.println(g.id + "   " + g.name + "   " + g.price + "    " + g.buyNumber);

            }
        }
        System.out.println("---------------------------\n\n");

        Tips1();
        Tips2();
    }

    public static void addGoods(Goods[] shopCar, Scanner sc) {
        System.out.println("----------添加商品----------");
        System.out.print("请输入商品名称:");
        String name = sc.next();
        System.out.print("请输入商品数量:");
        int buyNumber = sc.nextInt();
        System.out.print("请输入商品价格:");
        double price = sc.nextDouble();
        System.out.println("---------------------------\n\n");

        Tips1();
        int id;
        Goods g1 = new Goods();
        g1.name = name;
        g1.price = price;
        g1.buyNumber = buyNumber;
        for (int i = 0; i < shopCar.length; i++) {
            if (shopCar[i] == null){
                id = i + 1;
                g1.id = id;
                System.out.println("商品加入购物车中···");
                shopCar[i] = g1;
                break;
            }
        }
        System.out.println("商品成功加入购物车！");
        Tips2();
    }

    public static void Tips1(){
        System.out.println("----------处理信息----------");
        System.out.println("正在处理中，请稍后···");
    }

    public static void Tips2(){
        System.out.println("处理完成！");
        System.out.println("---------------------------\n\n");

    }
}
