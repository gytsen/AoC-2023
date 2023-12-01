const std = @import("std");

const Errors = error{
    DayNotImplementedError,
};

pub fn main() !void {
    // Get allocator
    var gpa = std.heap.GeneralPurposeAllocator(.{}){};
    const allocator = gpa.allocator();
    defer _ = gpa.deinit();

    // Parse args into string array (error union needs 'try')
    const args = try std.process.argsAlloc(allocator);
    defer std.process.argsFree(allocator, args);

    if (args.len > 2) {
        std.debug.print("expected either no or exactly one argument", .{});
        std.os.exit(1);
    } else if (args.len == 1) {
        std.debug.print("working on getting all days working", .{});
        std.os.exit(0);
    }

    const day = args[1];

    const parsed_day = try std.fmt.parseInt(u32, day, 10);

    try switch (parsed_day) {
        1 => day_one(),
        else => std.debug.print("day not implemented yet", .{}),
    };
}

pub fn read_file(day: u32) []u8 {
    _ = day;
}

pub fn day_one() !void {
    std.debug.print("wohoo, I got called", .{});
}
